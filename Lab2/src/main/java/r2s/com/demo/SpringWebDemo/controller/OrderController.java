package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.CreateOrderRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.CreateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateOrderRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.OrderResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingOrderListResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingProductListResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.ProductResponseDTO;
import r2s.com.demo.SpringWebDemo.service.OrderService;
import r2s.com.demo.SpringWebDemo.service.ProductService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/order")
public class OrderController {
    private OrderService orderService = new OrderService();

    @GetMapping
    public ResponseEntity<?> getAllOrder(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size,
                                        @RequestParam(value = "sort", required = false) String sort) {
        List<OrderResponseDTO> orderResponseDTOList = orderService.getListOrder();

        PagingOrderListResponseDTO response = new PagingOrderListResponseDTO();
        response.setOrderResponseDTOList(orderResponseDTOList);
        response.setPage(page);
        response.setSize(size);
        response.setSort(sort);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/{order-id}")
    public ResponseEntity getProductById(@PathVariable(value = "order-id") int orderId) {
        List<OrderResponseDTO> response = orderService.getListOrder()
                .stream()
                .filter(c -> c.getId() == orderId)
                .collect(Collectors.toList());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        int id = createOrderRequestDTO.getId();
        int cartId = createOrderRequestDTO.getCartId();
        long totalPay = createOrderRequestDTO.getTotalPay();
        Date orderDate = createOrderRequestDTO.getOrderDate();
        StringBuilder stringBuilder = new StringBuilder();
        String response = stringBuilder.append(id)
                .append(cartId)
                .append(totalPay)
                .append(orderDate)
                .toString();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{order-id}")
    public ResponseEntity updateOrder(@PathVariable(value = "order-id") int orderId,
                                      @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(orderId);
        response.setCartId(updateOrderRequestDTO.getCartId());
        response.setStatus(updateOrderRequestDTO.isStatus());
        response.setOrderDate(updateOrderRequestDTO.getOrderDate());
        response.setTotalPay(updateOrderRequestDTO.getTotalPay());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable(value = "order-id") int orderId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
