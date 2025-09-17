package pl.arwro.ecommerce.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.arwro.ecommerce.category.dto.CategoryDto;
import pl.arwro.ecommerce.category.dto.CreateCategoryRequest;
import pl.arwro.ecommerce.category.dto.UpdateCategoryRequest;
import pl.arwro.ecommerce.shared.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<CategoryDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public CategoryDto getById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category id=" + id + " not found"));
        return mapper.toDto(category);
    }

    public CategoryDto create(CreateCategoryRequest req) {
        Category entity = mapper.fromCreate(req);
        return mapper.toDto(repository.save(entity));
    }

    public CategoryDto update(Long id, UpdateCategoryRequest req) {
        Category entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category id=" + id + " not found"));
        mapper.update(entity, req);
        return mapper.toDto(entity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Category id=" + id + " not found");
        }
        repository.deleteById(id);
    }
}
