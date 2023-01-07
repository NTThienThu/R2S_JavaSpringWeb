package r2s.com.demo.SpringWebDemo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import r2s.com.demo.SpringWebDemo.dto.response.OrderResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Order;

import java.util.List;
@Component
public class OrderMapper {
    public List<OrderResponseDTO> convertEntiitiesToResponseDtos(List<Order> orderList){
        return orderList.stream().map(this:: convertEntiitiesToResponseDto).toList();
    }

    public OrderResponseDTO convertEntiitiesToResponseDto(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        BeanUtils.copyProperties( order, orderResponseDTO);
        return orderResponseDTO;
    }
}
