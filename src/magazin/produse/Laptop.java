package magazin.produse;

import magazin.distribuitori.Distributor;

public class Laptop extends Product{
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

    public void displayExtra() {
        System.out.println("Tipul procesorului: " + this.procesor);
        System.out.println();

    }
}
