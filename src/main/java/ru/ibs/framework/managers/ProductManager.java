package ru.ibs.framework.managers;

import ru.ibs.framework.utils.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductManager {

  private static ProductManager INSTANCE = null;

  private List<Product> productList = new ArrayList<>();

  public ProductManager() {}

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

  public Product maxPriceInShoppingCart() {
    return productList.stream().max(Comparator.comparingInt(Product::getPrice)).get();
  }

  public String report() {
    String result = "Самый дорогой товар в корзине - " + maxPriceInShoppingCart().getTitle() + "\n";
    for (Product p : productList) {
      result = result.concat("Наименование: " + p.getTitle() + "\n\tЦена: " + p.getPrice() + "\n");
    }
    return result;
  }
}
