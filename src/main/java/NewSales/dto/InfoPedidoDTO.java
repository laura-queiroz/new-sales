package NewSales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoPedidoDTO {

    private Integer code;
    private String nomeClient;
    private Double total;
    private String data;
    private String status;
    private List<InfoItemPedidoDTO> items;
}
