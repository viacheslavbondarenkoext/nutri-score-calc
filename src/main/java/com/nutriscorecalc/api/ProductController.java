package com.nutriscorecalc.api;

import com.nutriscorecalc.domain.ProductEntity;
import com.nutriscorecalc.domain.ProductRepository;
import com.nutriscorecalc.service.NutriScoreCalculator;
import com.nutriscorecalc.service.dto.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing products and calculating NutriScore.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repo;
    private final NutriScoreCalculator calculator;

    public ProductController(ProductRepository repo, NutriScoreCalculator calculator) {
        this.repo = repo;
        this.calculator = calculator;
    }

    @GetMapping
    public List<Product> list() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(e -> ResponseEntity.ok(toDto(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product dto) {
        ProductEntity entity = toEntity(dto);
        ProductEntity saved = repo.save(entity);
        Product out = toDto(saved);
        out.nutriScore = calculator.calculate(out);
        URI location = URI.create("/api/products/" + saved.id);
        return ResponseEntity.created(location).body(out);
    }

    private Product toDto(ProductEntity e) {
        Product p = new Product(e.id, e.name, e.energyKj, e.sugar, e.saturatedFat, e.sodium, e.fiber, e.protein, e.fruitsPercentage);
        p.nutriScore = calculator.calculate(p);
        return p;
    }

    private ProductEntity toEntity(Product p) {
        ProductEntity e = new ProductEntity(p.name, p.energyKj, p.sugar, p.saturatedFat, p.sodium, p.fiber, p.protein, p.fruitsPercentage);
        e.id = p.id;
        return e;
    }
}

