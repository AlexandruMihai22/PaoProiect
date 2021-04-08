package magazin.stocuri;

import magazin.categorii.Electronics;
import magazin.categorii.PersonalCare;
import magazin.produse.*;

public class Stock implements Comparable<Stock> {

    private String name;
    private PersonalCare pc;
    private Electronics el;

    public Stock(String name){
        this.name = name;
        this.pc = new PersonalCare();
        this.el = new Electronics();
    }

    public Stock(String name, Product product, int quantity){
        this.name = name;
        if(product instanceof Shampoo||product instanceof Parfum) {
            this.pc = new PersonalCare(product, quantity, " ");
            this.el = new Electronics();
        }
        if(product instanceof Laptop ||product instanceof Camera) {
            this.el = new Electronics(product, quantity);
            this.pc = new PersonalCare();
        }
    }

    public Stock(String name, PersonalCare pc, Electronics el) {
        this.name = name;
        this.pc = pc;
        this.el = el;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonalCare getPc() {
        return pc;
    }

    public void setPc(PersonalCare pc) {
        this.pc = pc;
    }

    public Electronics getEl() {
        return el;
    }

    public void setEl(Electronics el) {
        this.el = el;
    }

    @Override
    public int compareTo(Stock o) {

        return this.name.compareTo(o.name);
    }

}
