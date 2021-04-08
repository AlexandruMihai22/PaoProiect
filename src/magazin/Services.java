package magazin;

import magazin.produse.*;
import magazin.stocuri.Stock;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Services {

    public static void display()
    {
        System.out.println("BUNA ZIUA SI BUN VENIT IN APLICATIA NOASTRA!" +
                "  Puteti face urmatoarele operatii \n\n" +
                "1.Puteti adauga un produs intr-un anumit stoc \n"+
                "2.Puteti sterge un produs dintr-un stoc \n"+
                "3.Puteti modifica pretul unui produs dintr-un stoc\n"+
                "4.Puteti afisa toate produsele dintr-un stoc\n"+
                "5.Puteti creste pretul tuturor produselor dintr-un stoc cu un procentaj\n"+
                "6.Puteti scadea pretul tuturor produselor dintr-un stoc cu un procentaj\n"+
                "7.Puteti verifica pretul unui produs dintr-un stoc\n"+
                "8.Puteti adauga un stoc \n"+
                "9.Puteti sterge un stoc \n"+
                "10.Puteti afisa toate produsele de tip parfum \n"+
                "11.Puteti afisa cel mai scump produs dintr un stoc\n"+
                "12.Puteti afisa distribuitorul cu cele mai multe produse dintr-un stoc\n"

        );
    }

    public static void addProduct(Set<Stock> stocks, String stockName, Product product, int numberOfProducts) {
        for(Stock s :stocks)
            if(s.getName().equals(stockName))
                if(product instanceof Shampoo ||product instanceof Parfum)
                    s.getPc().addProduct(product, numberOfProducts);
                else if(product instanceof Laptop ||product instanceof Camera)
                    s.getEl().addProduct(product, numberOfProducts);

    }
    public static void display(Set<Stock> stocks){
        for(Stock s :stocks) {
            s.getEl().display();
            s.getPc().display();
        }
    }

    public static void removeProduct(Set<Stock> stocks, String stockName, String productName) {
        for(Stock s :stocks)
            if(s.getName().equals(stockName))
            {   if (s.getPc().SearchProduct(productName))
                s.getPc().removeProduct(productName);
                if (s.getEl().SearchProduct(productName))
                    s.getEl().removeProduct(productName);
            }
    }

    public static void modifyPrice(Set<Stock> stocks, String stockName, String productName, int newPrice) {
        for(Stock s :stocks)
            if(s.getName().equals(stockName))
            {   if (s.getPc().SearchProduct(productName))
                    s.getPc().modifyPrice(productName, newPrice);
                if (s.getEl().SearchProduct(productName))
                    s.getEl().modifyPrice(productName, newPrice);
            }
    }

    public static void checkPrice(Set<Stock> stocks, String stockName, String productName) {
        for (Stock s : stocks)
            if (s.getName().equals(stockName)) {
                if (s.getPc().SearchProduct(productName)) {
                    for (Map.Entry<Product, Integer> entry : s.getPc().getProducts().entrySet())
                        if (entry.getKey().getName().equals(productName))
                            System.out.println(entry.getKey().getPrice());
                }
                if (s.getEl().SearchProduct(productName)) {
                    for (Map.Entry<Product, Integer> entry : s.getEl().getProducts().entrySet())
                        if (entry.getKey().getName().equals(productName))
                            System.out.println(entry.getKey().getPrice());
                }
            }
    }

    public static void increasePrice(Set<Stock> stocks, String stockName, int percentage) {
        for(Stock s :stocks)
            if(s.getName().equals(stockName)) {
                s.getPc().increasePrice(percentage);
                s.getEl().increasePrice(percentage);
            }
    }

    public static void decreasePrice(Set<Stock> stocks, String stockName, int percentage) {
        for(Stock s :stocks)
            if(s.getName().equals(stockName)) {
                s.getPc().decreasePrice(percentage);
                s.getEl().decreasePrice(percentage);
            }
    }

    public static void mostExpensive(Set<Stock> stocks, String stockName) {
        for(Stock s :stocks)
            if(s.getName().equals(stockName)) {
                if (s.getPc().mostExpensive().getPrice() > s.getEl().mostExpensive().getPrice())
                    s.getPc().mostExpensive().display();
                else
                    s.getEl().mostExpensive().display();

            }
    }

    public static void addStock(Set<Stock> stocks) {
        Scanner in=new Scanner(System.in);
        System.out.println("");

    }

    public static void deleteStock(Set<Stock> stocks, String stockName) {
        stocks.removeIf(s -> s.getName().equals(stockName));
    }


}
