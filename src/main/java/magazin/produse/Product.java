package magazin.produse;

import magazin.distribuitori.Distributor;

public abstract class Product implements Comparable<Product> {
    private String name;
    private int price;
    private String color;
    private final Distributor distributor;

    public Product(String name, int price, String color, Distributor distributor) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.distributor= distributor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public int compareTo(Product product) {
        return Integer.compare(this.price, product.price);
    }

}
