package r2s.com.demo.SpringWebDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.demo.SpringWebDemo.dto.request.CreateCategoryRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.CreateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.UpdateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.CategoryResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.OrderResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingCategoryListResponseDTO;
import r2s.com.demo.SpringWebDemo.dto.response.PagingOrderListResponseDTO;
import r2s.com.demo.SpringWebDemo.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/category")
public class CategoryController {
    private CategoryService categoryService = new CategoryService();

    @GetMapping
    public ResponseEntity<?> getAllCategory(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size,
                                         @RequestParam(value = "sort", required = false) String sort) {
        List<CategoryResponseDTO> categoryResponseDTOList = categoryService.getListCategory();

        PagingCategoryListResponseDTO response = new PagingCategoryListResponseDTO();
        response.setCategoryResponseDTOList(categoryResponseDTOList);
        response.setPage(page);
        response.setSize(size);
        response.setSort(sort);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/{category-id}")
    public ResponseEntity getProductById(@PathVariable(value = "category-id") int categoryId) {
        List<CategoryResponseDTO> response = categoryService.getListCategory()
                .stream()
                .filter(c -> c.getId() == categoryId)
                .collect(Collectors.toList());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertcaegory(@RequestBody CreateCategoryRequestDTO categoryRequestDTO) {
        int id = categoryRequestDTO.getId();
        String name = categoryRequestDTO.getName();
        StringBuilder stringBuilder = new StringBuilder();
        String response = stringBuilder.append(id)
                .append(name)
                .toString();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{category-id}")
    public ResponseEntity updateCategory(@PathVariable(value = "category-id") int categoryId,
                                         @RequestBody UpdateProductRequestDTO updateProductRequestDTO) {
        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(categoryId);
        response.setName(updateProductRequestDTO.getName());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity deleteCategory(@PathVariable(value = "category-id") int categoryId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
