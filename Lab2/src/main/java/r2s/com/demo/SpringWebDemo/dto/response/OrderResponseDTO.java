package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private int id;
    private long totalPay;
    private Date orderDate;
    private boolean status;
    private int cartId;
}
