package magazin.produse;

import magazin.distribuitori.Distributor;

public class Shampoo extends Product {

    private String hairType;

    public Shampoo(String name, int price, String color, Distributor distributor, String hairType) {
        super(name, price, color, distributor);
        this.hairType = hairType;

    }

    public String getHairType() {
        return hairType;

    }

    public void setHairType(String hairType) {
        this.hairType = hairType;

    }

    public void displayExtra() {
        System.out.println(this.hairType);
        System.out.println();

    }

}

