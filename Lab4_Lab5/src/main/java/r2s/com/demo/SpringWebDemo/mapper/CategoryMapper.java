package r2s.com.demo.SpringWebDemo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import r2s.com.demo.SpringWebDemo.dto.response.CategoryResponseDTO;
import r2s.com.demo.SpringWebDemo.entity.Category;

import java.util.List;
@Component
public class CategoryMapper {
    public List<CategoryResponseDTO> convertEntiitiesToResponseDtos(List<Category> categoryList){
        return categoryList.stream().map(this:: convertEntiitiesToResponseDto).toList();
    }

    public CategoryResponseDTO convertEntiitiesToResponseDto(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        BeanUtils.copyProperties( category, categoryResponseDTO);
        return categoryResponseDTO;
    }
}
