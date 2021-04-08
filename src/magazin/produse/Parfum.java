package magazin.produse;


import magazin.distribuitori.Distributor;

public class Parfum extends Product {

    private String parfumType;

    public Parfum(String name, int price, String color, Distributor distributor, String parfumType) {
        super(name, price, color, distributor);
        this.parfumType = parfumType;

    }

    public String getParfumType() {
        return parfumType;

    }

    public void SetParfumType(String parfumType) {
        this.parfumType = parfumType;

    }

    public void displayExtra() {
        System.out.println("Tipul parfumului: " + this.parfumType);
        System.out.println();

    }

}
