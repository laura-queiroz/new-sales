package NewSales.service.implementation;


import NewSales.dto.InfoPedidoDTO;
import NewSales.dto.ItemPedidoDTO;
import NewSales.dto.PedidoDTO;
import NewSales.entities.Client;
import NewSales.entities.ItemPedido;
import NewSales.entities.Pedido;
import NewSales.entities.Product;
import NewSales.enums.StatusPedido;
import NewSales.exceptions.BusinessRulesException;
import NewSales.exceptions.PedidoNaoEncontradoException;
import NewSales.repository.ClientRepository;
import NewSales.repository.ItemPedidoRepository;
import NewSales.repository.PedidoRepository;
import NewSales.repository.ProductRepository;
import NewSales.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidorep;

    @Autowired
    private ClientRepository cRep;

    @Autowired
    private ProductRepository pRep;

    @Autowired
    private ItemPedidoRepository itRep;

    @Override
    @Transactional
    public Pedido save(PedidoDTO dto) {
        Integer idClient = dto.getClient();
        Client c = cRep
                .findById(idClient)
                .orElseThrow(() -> new BusinessRulesException("Código de cliente inválido"));

        Pedido p = new Pedido();
        p.setTotal(dto.getTotal());
        p.setDataPedido(LocalDate.now());
        p.setClient(c);
        p.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(p, dto.getItems());
        p.setItens(itemsPedido);
        pedidorep.save(p);
        itRep.saveAll(itemsPedido);
        return p;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new BusinessRulesException("Não é possível fazer um pedido sem items"); }
        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProd();
                    Product prod = pRep.findById(idProduto)
                            .orElseThrow(() -> new BusinessRulesException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuant());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(prod);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<Pedido> getCompletePedido(Integer id) {
        return pedidorep.findByIdFecthItens(id);
    }

    @Override
    @Transactional
    public void atualizarStatusPedido(Integer id, StatusPedido status) {
        pedidorep.findById(id)
                .map(pedido ->
                        {pedido.setStatus(status);
                return pedidorep.save(pedido);}
                ).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

}
