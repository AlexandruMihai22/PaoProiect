package magazin.categorii;

import magazin.categorii.Category;
import magazin.produse.Product;

import java.util.Map;

public class PersonalCare extends Category {
    private String information;

    public PersonalCare() {
        this.information = " ";
    }

    public PersonalCare(Product product, int quantity, String information){
        super(product, quantity);
        this.information = information;
    }

    public PersonalCare(Map<Product, Integer> products, String information) {
        super(products);
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
