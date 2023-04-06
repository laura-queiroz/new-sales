package NewSales.repository;

import NewSales.entities.Client;
import NewSales.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByClient(Client c);

    @Query( " select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFecthItens (Integer id);
}

