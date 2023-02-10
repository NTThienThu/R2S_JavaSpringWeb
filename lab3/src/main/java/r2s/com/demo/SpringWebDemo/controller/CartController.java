package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.InsertCartRequestDTO;
import r2s.com.demo.SpringWebDemo.entity.Cart;
import r2s.com.demo.SpringWebDemo.service.CartService;

import java.util.List;

@RestController()
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<?> getAllCart() {
        List<Cart> cartList = cartService.getAllCartDatabase();
        return new ResponseEntity(cartList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity insertCart(@RequestBody InsertCartRequestDTO requestDTO) {
        Cart cart = cartService.insertCart(requestDTO);
        return new ResponseEntity(cart, HttpStatus.OK);
    }

//    @GetMapping("/{cart-id}")
//    public ResponseEntity getProductById(@PathVariable(value = "cart-id") int cartId) {
//        List<CartResponseDTO> response = cartService.getListCart()
//                .stream()
//                .filter(c -> c.getId() == cartId)
//                .collect(Collectors.toList());
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity insertcaegory(@RequestBody CreateCartRequestDTO createCartRequestDTO) {
//        int id = createCartRequestDTO.getId();
//        int userId = createCartRequestDTO.getUserId();
//        StringBuilder stringBuilder = new StringBuilder();
//        String response = stringBuilder.append(id)
//                .append(userId)
//                .toString();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//    @PutMapping("/{cart-id}")
//    public ResponseEntity updateCart(@PathVariable(value = "cart-id") int cartId,
//                                     @RequestBody UpdateCartRequestDTO updateCartRequestDTO) {
//        CartResponseDTO response = new CartResponseDTO();
//        response.setId(cartId);
//        response.setUserId(updateCartRequestDTO.getUserId());
//
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{cart-id}")
//    public ResponseEntity deleteCart(@PathVariable(value = "cart-id") int cartId) {
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
}
