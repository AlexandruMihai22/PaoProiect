package magazin.produse;

import magazin.distribuitori.Distributor;

public class Camera extends Product{
    private int megapixels;

    public Camera(String name, int price, String color, Distributor distributor, int megapixels) {
        super(name, price, color, distributor);
        this.megapixels = megapixels;
    }

    public int getMegapixels() {
        return megapixels;
    }

    public void setMegapixels(int megapixels) {
        this.megapixels = megapixels;
    }

    public void displayExtra() {
        System.out.println("Numarul de megapixeli: " + this.megapixels);
        System.out.println();

    }
}
