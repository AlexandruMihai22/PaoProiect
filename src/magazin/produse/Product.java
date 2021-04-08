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

    public void display() {
        System.out.println("Numele: " + this.name);
        System.out.println();
        System.out.println("Pretul: " + this.price);
        System.out.println();
        System.out.println("Culoarea: " + this.color);
        System.out.println();
        this.distributor.display();
        this.displayExtra();
    }

    public void displayExtra() {


    }

    public int compareTo(Product product) {
        return Integer.compare(this.price, product.price);
    }

}
