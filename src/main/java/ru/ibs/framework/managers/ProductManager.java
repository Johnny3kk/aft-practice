package ru.ibs.framework.managers;

import ru.ibs.framework.utils.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    private static ProductManager INSTANCE = null;

    private List<Product> productList = new ArrayList<>();

    public ProductManager() {
    }

    public static ProductManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductManager();
        }
        return INSTANCE;
    }

    public void add(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }
}
