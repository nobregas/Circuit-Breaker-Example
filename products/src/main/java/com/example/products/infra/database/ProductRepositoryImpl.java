package com.example.products.infra.database;

import com.example.products.domain.Product;
import com.example.products.domain.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private static final List<Product> PRODUCTS = new ArrayList<>();
    private static long id = 1;

    //Inicializador dos statics
    static {
        PRODUCTS.add(new Product(nextId(), "Desktop 4GB"));
        PRODUCTS.add(new Product(nextId(), "Laptop 4GB"));
        PRODUCTS.add(new Product(nextId(), "Laptop 8GB"));
    }

    @Override
    public void save(Product produto) {
        produto.setId(nextId());
        PRODUCTS.add(produto);
    }

    @Override
    public Optional<Product> getOne(Long id) {
        return PRODUCTS.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(PRODUCTS);
    }

    private static long nextId() {
        return id++;
    }
}
