package pl.arwro.ecommerce.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.arwro.ecommerce.category.dto.CategoryDto;
import pl.arwro.ecommerce.category.dto.CreateCategoryRequest;
import pl.arwro.ecommerce.category.dto.UpdateCategoryRequest;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<CategoryDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CreateCategoryRequest request) {
        CategoryDto created = service.create(request);
        return ResponseEntity.created(URI.create("/category/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id, @Valid @RequestBody UpdateCategoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
