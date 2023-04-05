package NewSales.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "item_pedidos")
@Data
public class ItemPedido {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Product produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    public ItemPedido(){};

    public ItemPedido(Integer id, Pedido pedido, Product produto, Integer quantidade) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;}

}
