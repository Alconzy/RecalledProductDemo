package com.apex.eqp.inventory.controllers;

import com.apex.eqp.inventory.entities.Product;
import com.apex.eqp.inventory.entities.RecalledProduct;
import com.apex.eqp.inventory.helpers.ProductFilter;
import com.apex.eqp.inventory.repositories.InventoryRepository;
import com.apex.eqp.inventory.repositories.RecalledProductRepository;
import com.apex.eqp.inventory.services.ProductService;
import com.apex.eqp.inventory.services.RecalledProductService;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @MockBean
    RecalledProductService recalledProductService;

    @MockBean
    ProductService productService;

    @MockBean
    InventoryController inventoryController;

    @MockBean
    InventoryRepository inventoryRepository;

    @MockBean
    RecalledProductRepository recalledProductRepository;

    ProductFilter productFilter;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    Product product;
    RecalledProduct recalledProduct;

    @Before
    public void setup() {
        recalledProduct = RecalledProduct.builder()
                .id(1)
                .name("Apple")
                .build();
        List<String> recalledProducts = new ArrayList<>();
        recalledProducts.add(recalledProduct.getName());
        productFilter = new ProductFilter(recalledProducts);
    }

    @Test
    void getAllProducts() throws Exception {
        /**
         *  @Id
         *     @GeneratedValue
         *     Integer id;
         *
         *     @Column(unique=true)
         *     String name;
         *     double price;
         *     Integer quantity;
         */
        //create products
        //create recallProducts
        //call getAll..
       // return ResponseEntity.ok(productService.getAllProduct());
        // return filter.removeRecalled(inventoryRepository.findAll());
        // allProduct.stream().filter(a -> filterByName(a)).collect(Collectors.toList());

        //create some products (all not recalled)
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Apple", 123,1));
        when(productService.getAllProduct()).thenReturn(products);
        given(inventoryRepository.findAll()).willReturn(productFilter.removeRecalled(products));
        MvcResult mvcResult = mockMvc.perform(get("api/inventory/product"))
                .andExpect(status().isOk())
               .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);
    }
}