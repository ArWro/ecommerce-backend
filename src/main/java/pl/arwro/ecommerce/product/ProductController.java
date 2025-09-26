package pl.arwro.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.arwro.ecommerce.product.dto.ProductDto;
import pl.arwro.ecommerce.product.dto.CreateProductRequest;
import pl.arwro.ecommerce.product.dto.UpdateProductRequest;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody CreateProductRequest request) {
        ProductDto created = service.create(request);
        return ResponseEntity.created(URI.create("/product/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

