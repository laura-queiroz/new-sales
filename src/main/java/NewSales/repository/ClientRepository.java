package NewSales.repository;

import NewSales.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = " select c from Client c where c.nome like %:nome% ")
    List<Client> encontrarPorNome(@Param("nome") String nome);


    @Query(value = "delete from Client c where c.nome =:nome")
    @Modifying
    void deleteByName(String nome);

    boolean existsByNome(String nome);

    @Query("select c from Client c left join fetch c.pedidos where c.id =:id")
    Client findFetch(@Param("id") Integer id);


    List<Client> findAll();

}


