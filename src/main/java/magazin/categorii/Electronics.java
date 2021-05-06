package magazin.categorii;

import magazin.categorii.Category;
import magazin.produse.Product;

import java.util.Map;

public class Electronics extends Category {
    public Electronics() {
    }

    public Electronics(Product product, int quantity) {
        super(product, quantity);
    }

    public Electronics(Map<Product, Integer> products) {
        super(products);
    }
}
