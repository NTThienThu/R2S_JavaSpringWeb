package r2s.com.demo.SpringWebDemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartRequestDTO {
    private int id;
    private int userId;
}
