package magazin.produse;

import magazin.distribuitori.Distributor;

public class Camera extends Product{
    private int megapixels;

    public Camera(String name, int price, String color, Distributor distributor, int megapixels) {
        super(name, price, color, distributor);
        this.megapixels = megapixels;
    }


}
