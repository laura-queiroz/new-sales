package NewSales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoItemPedidoDTO {
    private String descricaoProduto;
    private Double precoUnitario;
    private Integer quantidade;
}
