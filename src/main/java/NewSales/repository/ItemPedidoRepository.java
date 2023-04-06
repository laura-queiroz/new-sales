package NewSales.repository;

import NewSales.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
