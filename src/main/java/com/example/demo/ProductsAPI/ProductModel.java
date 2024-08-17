package com.example.demo.ProductsAPI;

public class ProductModel {
  public enum ProductCategory {
    FOOD,
    DRINKS,
    ENTERTAINMENT,
    SCHOOL,
    ELECTRONICS,
    GAMES,
    HOME_GOODS,
    SPORTS,
    TOYS,
    OTHER,
  }

  private String name;
  private double price;
  private String id;
  private ProductCategory category;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public ProductCategory getCategory() {
    return category;
  }

  public void setCategory(ProductCategory category) {
    this.category = category;
  }
}
