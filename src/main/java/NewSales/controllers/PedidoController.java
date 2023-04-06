package NewSales.controllers;


import NewSales.dto.*;
import NewSales.entities.ItemPedido;
import NewSales.entities.Pedido;
import NewSales.enums.StatusPedido;
import NewSales.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/pedidos")
@Transactional
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping("/save")
    @ResponseStatus(CREATED)
    public Integer save(@Valid @RequestBody PedidoDTO dto){
        Pedido p = service.save(dto);
        return p.getId();}

    @GetMapping("/{id}")
    public InfoPedidoDTO getById(@PathVariable Integer id){
        return service.getCompletePedido(id)
                .map(p-> converter(p) )
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    private InfoPedidoDTO converter(Pedido ped){
     return InfoPedidoDTO.builder()
               .code(ped.getId())
               .data(ped.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
               .nomeClient(ped.getClient().getNome())
               .total(ped.getTotal())
                .status(ped.getStatus().name())
               .items(converterItem(ped.getItens()))
               .build();}

    private List<InfoItemPedidoDTO> converterItem (List<ItemPedido> items){
        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();}

        return items.stream()
                .map(item -> InfoItemPedidoDTO.builder()
                        .descricaoProduto(item.getProduto().getDescrição())
                        .precoUnitario(item.getProduto().getPreçoUnitario())
                        .quantidade(item.getQuantidade())
                        .build()).collect(Collectors.toList()); }

    @PatchMapping("/{id}/update")
    @ResponseStatus(NO_CONTENT)
    public void updateStatusPedido(@PathVariable Integer id,
                                   @Valid @RequestBody AtualizarStatusPedidoDTO statusPedidoDTO) {
        String newStatus = statusPedidoDTO.getStatus();
        service.atualizarStatusPedido(id,
                StatusPedido.valueOf(newStatus));

    }
}
