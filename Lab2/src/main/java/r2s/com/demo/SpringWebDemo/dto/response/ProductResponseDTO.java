package r2s.com.demo.SpringWebDemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private int id;
    private String name;
    private long price;
    private String salerName;
    private boolean isDeleted;
    private int categoryId;
}
