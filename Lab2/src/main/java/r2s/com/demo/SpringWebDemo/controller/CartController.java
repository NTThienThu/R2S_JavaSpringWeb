package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.CreateCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.CreateCategoryRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateCartRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CartResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CategoryResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingCartListResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingCategoryListResponseDTO;
import r2s.com.demo.SpringWebDemo.service.CartService;
import r2s.com.demo.SpringWebDemo.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/cart")
public class CartController {
    private CartService cartService = new CartService();

    @GetMapping
    public ResponseEntity<?> getAllCart(@RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size,
                                            @RequestParam(value = "sort", required = false) String sort) {
        List<CartResponseDTO> categoryResponseDTOList = cartService.getListCart();

        PagingCartListResponseDTO response = new PagingCartListResponseDTO();
        response.setCartResponseDTOList(categoryResponseDTOList);
        response.setPage(page);
        response.setSize(size);
        response.setSort(sort);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/{cart-id}")
    public ResponseEntity getProductById(@PathVariable(value = "cart-id") int cartId) {
        List<CartResponseDTO> response = cartService.getListCart()
                .stream()
                .filter(c -> c.getId() == cartId)
                .collect(Collectors.toList());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertcaegory(@RequestBody CreateCartRequestDTO createCartRequestDTO) {
        int id = createCartRequestDTO.getId();
        int userId = createCartRequestDTO.getUserId();
        StringBuilder stringBuilder = new StringBuilder();
        String response = stringBuilder.append(id)
                .append(userId)
                .toString();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{cart-id}")
    public ResponseEntity updateCart(@PathVariable(value = "cart-id") int cartId,
                                     @RequestBody UpdateCartRequestDTO updateCartRequestDTO) {
        CartResponseDTO response = new CartResponseDTO();
        response.setId(cartId);
        response.setUserId(updateCartRequestDTO.getUserId());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{cart-id}")
    public ResponseEntity deleteCart(@PathVariable(value = "cart-id") int cartId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
