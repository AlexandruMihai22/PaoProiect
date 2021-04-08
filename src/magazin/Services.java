package magazin;

import magazin.distribuitori.Distributor;
import magazin.produse.*;
import magazin.stocuri.Stock;

import java.util.HashMap;
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
                "10.Puteti afisa cel mai scump produs dintr un stoc\n"+
                "11.Puteti afisa toate produsele de tip parfum \n"+
                "12.Puteti afisa toate produsele de la un anumit distribuitor\n"
        );
    }

    public static void addProduct(Stock s, Product product, int numberOfProducts) {
                if(product instanceof Shampoo ||product instanceof Parfum)
                    s.getPc().addProduct(product, numberOfProducts);
                else if(product instanceof Laptop ||product instanceof Camera)
                    s.getEl().addProduct(product, numberOfProducts);
    }

    public static void display(Set<Stock> stocks){
        for(Stock s :stocks) {
            for (Map.Entry<Product, Integer> entry : s.getEl().getProducts().entrySet())
                System.out.println(entry.getKey());
            for (Map.Entry<Product, Integer> entry : s.getPc().getProducts().entrySet())
                System.out.println(entry.getKey());
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
                    System.out.println(s.getPc().mostExpensive());
                else
                    System.out.println(s.getEl().mostExpensive());
            }
    }

    public static void addStock(Set<Stock> stocks) {
        Scanner in=new Scanner(System.in);
        System.out.println("Introduceti numele stocului");
        String stockName=in.next();
        Stock stock = new Stock(stockName);

        System.out.println("Cate produse de tip Parfum doriti sa aiba stocul?");
        int nrParfum=in.nextInt();
        for(int i = 0; i < nrParfum; i++ ) {
            System.out.println("Care este numele produsului?");
            String productName = in.next();
            System.out.println("Care este pretul produsului?");
            int productPrice = in.nextInt();
            System.out.println("Care este culoarea produsului?");
            String productColor = in.next();
            System.out.println("Care este tipul parfumului");
            String parfumType = in.next();
            System.out.println("Cate produse de acest tip se afla in stoc?");
            int nr = in.nextInt();
            System.out.println("Care este numele distribuitorului?");
            String distributorName = in.next();
            System.out.println("Care este adresa distribuitorului?");
            String distributorAddress = in.next();
            System.out.println("Care este numarul de telefon al distribuitorului?");
            String distributorPhoneNumber = in.next();
            Distributor productDistributor = new Distributor(distributorName, distributorAddress, distributorPhoneNumber);
            Parfum parfum = new Parfum(productName, productPrice, productColor, productDistributor, parfumType);
            addProduct(stock, parfum , nr);
        }

        System.out.println("Cate produse de tip Sampon doriti sa aiba stocul?");
        int nrShampoo=in.nextInt();
        for(int i = 0; i < nrShampoo; i++ ) {
            System.out.println("Care este numele produsului?");
            String productName = in.next();
            System.out.println("Care este pretul produsului?");
            int productPrice = in.nextInt();
            System.out.println("Care este culoarea produsului?");
            String productColor = in.next();
            System.out.println("Care este tipul parfumului");
            String shampooType = in.next();
            System.out.println("Cate produse de acest tip se afla in stoc?");
            int nr = in.nextInt();
            System.out.println("Care este numele distribuitorului?");
            String distributorName = in.next();
            System.out.println("Care este adresa distribuitorului?");
            String distributorAddress = in.next();
            System.out.println("Care este numarul de telefon al distribuitorului?");
            String distributorPhoneNumber = in.next();
            Distributor productDistributor = new Distributor(distributorName, distributorAddress, distributorPhoneNumber);
            Shampoo shampoo = new Shampoo(productName, productPrice, productColor, productDistributor, shampooType);
            addProduct(stock, shampoo , nr);
        }

        System.out.println("Cate produse de tip Laptop doriti sa aiba stocul?");
        int nrLaptop=in.nextInt();
        for(int i = 0; i < nrLaptop; i++ ) {
            System.out.println("Care este numele produsului?");
            String productName = in.next();
            System.out.println("Care este pretul produsului?");
            int productPrice = in.nextInt();
            System.out.println("Care este culoarea produsului?");
            String productColor = in.next();
            System.out.println("Care este tipul procesorului");
            String procesorType = in.next();
            System.out.println("Cate produse de acest tip se afla in stoc?");
            int nr = in.nextInt();
            System.out.println("Care este numele distribuitorului?");
            String distributorName = in.next();
            System.out.println("Care este adresa distribuitorului?");
            String distributorAddress = in.next();
            System.out.println("Care este numarul de telefon al distribuitorului?");
            String distributorPhoneNumber = in.next();
            Distributor productDistributor = new Distributor(distributorName, distributorAddress, distributorPhoneNumber);
            Laptop laptop = new Laptop(productName, productPrice, productColor, productDistributor, procesorType);
            addProduct(stock, laptop , nr);
        }

        System.out.println("Cate produse de tip Camera doriti sa aiba stocul?");
        int nrCamera=in.nextInt();
        for(int i = 0; i < nrCamera; i++ ) {
            System.out.println("Care este numele produsului?");
            String productName = in.next();
            System.out.println("Care este pretul produsului?");
            int productPrice = in.nextInt();
            System.out.println("Care este culoarea produsului?");
            String productColor = in.next();
            System.out.println("Care este tipul procesorului");
            int megapixels = in.nextInt();
            System.out.println("Cate produse de acest tip se afla in stoc?");
            int nr = in.nextInt();
            System.out.println("Care este numele distribuitorului?");
            String distributorName = in.next();
            System.out.println("Care este adresa distribuitorului?");
            String distributorAddress = in.next();
            System.out.println("Care este numarul de telefon al distribuitorului?");
            String distributorPhoneNumber = in.next();
            Distributor productDistributor = new Distributor(distributorName, distributorAddress, distributorPhoneNumber);
            Camera camera = new Camera(productName, productPrice, productColor, productDistributor, megapixels);
            addProduct(stock, camera , nr);
        }

        stocks.add(stock);
    }

    public static void deleteStock(Set<Stock> stocks, String stockName) {
        stocks.removeIf(s -> s.getName().equals(stockName));
    }

    public static void distributorProducts (Set<Stock> stocks, String distributorName) {
        for(Stock s :stocks) {
            for (Map.Entry<Product, Integer> entry : s.getEl().getProducts().entrySet())
                if(entry.getKey().getDistributor().getName().equals(distributorName))
                    System.out.println(entry.getKey());
            for (Map.Entry<Product, Integer> entry : s.getPc().getProducts().entrySet())
                if(entry.getKey().getDistributor().getName().equals(distributorName))
                    System.out.println(entry.getKey());
        }
    }

    public static void printParfums (Set<Stock> stocks) {
        for(Stock s :stocks) {
            for (Map.Entry<Product, Integer> entry : s.getPc().getProducts().entrySet())
                if(entry.getKey() instanceof Parfum)
                    System.out.println(entry.getKey());
        }
    }


}
