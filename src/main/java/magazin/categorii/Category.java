package magazin.categorii;

import java.util.Map;
import java.util.HashMap;

import magazin.produse.Product;

public abstract class Category {

    private Map<Product,Integer> products = new HashMap<>();

    public Category() {}

    public Category(Product product, int quantity) {
        this.products.put(product, quantity);

    }

    public Category(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return this.products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void addProduct(Product product, int quantity){
        this.products.put(product, quantity);

    }

    public void removeProduct(String productName) {
        this.products.keySet().removeIf(key -> key.getName().equals(productName));
    }

    public boolean searchProduct(String productName) {
       for (Map.Entry<Product, Integer> entry : this.products.entrySet())
           return entry.getKey().getName().equals(productName);
       return false;
    }

    public void modifyPrice(String productName, int newPrice) {
        for (Map.Entry<Product, Integer> entry : products.entrySet())
            if (entry.getKey().getName().equals(productName))
                entry.getKey().setPrice(newPrice);

    }
    public void increasePrice(int percentage) {
        for (Map.Entry<Product, Integer> entry : products.entrySet())
                entry.getKey().setPrice(entry.getKey().getPrice()*(100+percentage)/100);
    }

    public void decreasePrice(int percentage) {
        for (Map.Entry<Product, Integer> entry : products.entrySet())
            entry.getKey().setPrice(entry.getKey().getPrice()*(100-percentage)/100);
    }

    public Product mostExpensive() {
        Product p = null;
        int price = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet())
             if(entry.getKey().getPrice() > price)
                 p=entry.getKey();
        return p;
    }

}
