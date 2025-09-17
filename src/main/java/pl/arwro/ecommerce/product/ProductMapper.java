package pl.arwro.ecommerce.product;

import org.springframework.stereotype.Component;
import pl.arwro.ecommerce.category.Category;
import pl.arwro.ecommerce.product.dto.ProductDto;

@Component
public class ProductMapper {

    public ProductDto toDto(Product entity) {
        if (entity == null) return null;
        Long categoryId = null;
        Category cat = entity.getCategory();
        if (cat != null) categoryId = cat.getId();
        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .sku(entity.getSku())
                .categoryId(categoryId)
                .build();
    }
}
