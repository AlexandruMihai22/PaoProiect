package magazin.categorii;

import magazin.categorii.Category;
import magazin.produse.Product;

import java.util.Map;

public class PersonalCare extends Category {
    public PersonalCare() {
    }

    public PersonalCare(Product product, int quantity) {
        super(product, quantity);
    }

    public PersonalCare(Map<Product, Integer> products) {
        super(products);
    }
}
