package pl.arwro.ecommerce.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.arwro.ecommerce.category.Category;
import pl.arwro.ecommerce.category.CategoryRepository;
import pl.arwro.ecommerce.product.dto.ProductDto;
import pl.arwro.ecommerce.product.dto.CreateProductRequest;
import pl.arwro.ecommerce.product.dto.UpdateProductRequest;
import pl.arwro.ecommerce.shared.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public List<ProductDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public ProductDto getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product id=" + id + " not found"));
        return mapper.toDto(product);
    }

    public ProductDto create(CreateProductRequest req) {
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category id=" + req.getCategoryId() + " not found"));

        Product entity = Product.builder()
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .sku(req.getSku())
                .category(category)
                .build();

        return mapper.toDto(repository.save(entity));
    }

    public ProductDto update(Long id, UpdateProductRequest req) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product id=" + id + " not found"));

        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category id=" + req.getCategoryId() + " not found"));

        entity.setName(req.getName());
        entity.setDescription(req.getDescription());
        entity.setPrice(req.getPrice());
        entity.setSku(req.getSku());
        entity.setCategory(category);

        return mapper.toDto(entity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Product id=" + id + " not found");
        }
        repository.deleteById(id);
    }
}

