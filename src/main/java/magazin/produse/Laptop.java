package magazin.produse;

import magazin.distribuitori.Distributor;

public class Laptop extends Product {
    private String procesor;

    public Laptop(String name, int price, String color, Distributor distributor, String procesor) {
        super(name, price, color, distributor);
        this.procesor = procesor;
    }

    public String getProcesor() {
        return procesor;
    }

    public void setProcesor(String procesor) {
        this.procesor = procesor;
    }

    @Override
    public String toString() {
        return ("Numele: " + this.getName() + " " + "Pretul: " + this.getPrice() + " " +
                "Culoarea: " + this.getColor() + " " + "Distribuitor: " + this.getDistributor().getName() + " " + "Tipul procesorului: " + this.getProcesor());
    }

}
