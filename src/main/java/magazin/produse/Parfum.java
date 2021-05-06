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

    @Override
    public String toString() {
        return ("Numele: " + this.getName() + " " + "Pretul: " + this.getPrice() + " " +
                "Culoarea: " + this.getColor() + " " + "Distribuitor: " + this.getDistributor().getName() + " " + "Tipul parfumului: " + this.getParfumType());
    }

}
