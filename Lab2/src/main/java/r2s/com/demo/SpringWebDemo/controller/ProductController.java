package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.CreateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.CreateUserRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingProductListResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingUserListResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.ProductResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;
import r2s.com.demo.SpringWebDemo.service.ProductService;
import r2s.com.demo.SpringWebDemo.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/product")
public class ProductController {
    private ProductService productService = new ProductService();
    @GetMapping
    public ResponseEntity<?> getAllProduct(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size,
                                        @RequestParam(value = "sort", required = false) String sort) {
        List<ProductResponseDTO> productResponseDTOList = productService.getListProduct();

        PagingProductListResponseDTO response = new PagingProductListResponseDTO();
        response.setProductResponseDTOList(productResponseDTOList);
        response.setPage(page);
        response.setSize(size);
        response.setSort(sort);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity getProductById(@PathVariable(value = "product-id") int productId) {
        List<ProductResponseDTO> response = productService.getListProduct()
                .stream()
                .filter(c -> c.getId() == productId)
                .collect(Collectors.toList());
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity insertProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        int id = createProductRequestDTO.getId();
        String name = createProductRequestDTO.getName();
        long price = createProductRequestDTO.getPrice();
        String salerName = createProductRequestDTO.getSalerName();
        int categoryId = createProductRequestDTO.getCategoryId();
        StringBuilder stringBuilder = new StringBuilder();
        String response = stringBuilder.append(id)
                .append(name)
                .append(price)
                .append(salerName)
                .append(categoryId)
                .toString();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{product-id}")
    public ResponseEntity updateProduct(@PathVariable(value = "product-id") int productId,
                                        @RequestBody UpdateProductRequestDTO updateProductRequestDTO) {
        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(productId);
        response.setName(updateProductRequestDTO.getName());
        response.setPrice(updateProductRequestDTO.getPrice());
        response.setCategoryId(updateProductRequestDTO.getCategoryId());
        response.setSalerName(updateProductRequestDTO.getSalerName());
        response.setDeleted(updateProductRequestDTO.isDeleted());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "product-id") int productId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
