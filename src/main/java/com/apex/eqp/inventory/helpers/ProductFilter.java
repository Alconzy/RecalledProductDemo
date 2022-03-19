package com.apex.eqp.inventory.helpers;

import com.apex.eqp.inventory.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFilter {

    private static List<String> recalledProducts;

    public ProductFilter(List<String> recalledProducts) {
        if (recalledProducts == null) recalledProducts = new ArrayList<>();
        this.recalledProducts = recalledProducts;
    }

    public List<Product> removeRecalled(Collection<Product> allProduct) {
        return allProduct.stream().filter(a -> filterByName(a)).collect(Collectors.toList());
    }

    private static boolean filterByName(Product product) {
        if (!recalledProducts.contains(product.getName()))
            return true;
        return false;
    }
}
