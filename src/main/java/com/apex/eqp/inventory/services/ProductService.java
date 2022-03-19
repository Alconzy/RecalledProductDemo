package com.apex.eqp.inventory.services;

import com.apex.eqp.inventory.entities.Product;
import com.apex.eqp.inventory.entities.RecalledProduct;
import com.apex.eqp.inventory.helpers.ProductFilter;
import com.apex.eqp.inventory.repositories.InventoryRepository;
import com.apex.eqp.inventory.repositories.RecalledProductRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final InventoryRepository inventoryRepository;

    @Autowired
    RecalledProductService recalledProductService;

    public Product save(Product product) {
        return inventoryRepository.save(product);
    }

    public Collection<Product> getAllProduct() {
        Collection<RecalledProduct> recalledProducts = recalledProductService.getAllRecalledProducts();
        List<String> recallProductsName = new ArrayList<>();
        for (RecalledProduct recalledProduct : recalledProducts)
            recallProductsName.add(recalledProduct.getName());

        ProductFilter filter = new ProductFilter(recallProductsName);
        return filter.removeRecalled(inventoryRepository.findAll());
    }

    public Optional<Product> findById(Integer id) {
        return inventoryRepository.findById(id);
    }
}
