package pl.arwro.ecommerce.category;

import org.springframework.stereotype.Component;
import pl.arwro.ecommerce.category.dto.CategoryDto;
import pl.arwro.ecommerce.category.dto.CreateCategoryRequest;
import pl.arwro.ecommerce.category.dto.UpdateCategoryRequest;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category entity) {
        if (entity == null) return null;
        return CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public Category fromCreate(CreateCategoryRequest req) {
        if (req == null) return null;
        return Category.builder()
                .name(req.getName())
                .description(req.getDescription())
                .build();
    }

    public void update(Category entity, UpdateCategoryRequest req) {
        entity.setName(req.getName());
        entity.setDescription(req.getDescription());
    }
}
