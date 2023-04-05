package NewSales.entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descrição;

    @Column(name = "preço_unitario")
    @NotNull(message = "{campo.preco.obrigatorio}")
    private Double preçoUnitario;

    public Product(){};

    public Product(Integer id, String descrição, Double preçoUnitario) {
        this.id = id;
        this.descrição = descrição;
        this.preçoUnitario = preçoUnitario;
    }


}
