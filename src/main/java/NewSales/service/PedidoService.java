package NewSales.service;

import NewSales.dto.PedidoDTO;
import NewSales.entities.Pedido;
import NewSales.enums.StatusPedido;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PedidoService {

    Pedido save (PedidoDTO dto);

    Optional<Pedido> getCompletePedido (Integer id);

    void atualizarStatusPedido (Integer id, StatusPedido status);
}
