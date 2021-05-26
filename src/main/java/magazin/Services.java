package magazin;

import magazin.distribuitori.Distributor;
import magazin.produse.*;
import magazin.stocuri.Stock;

import java.io.BufferedWriter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

    //etapa2

    public static List<Product> readProducts() {
        return Singleton.getInstance().readProducts("Products.csv");
    }

    public static void addProductsFromCSV(Stock s, List<Product> products ){
        for(Product product : products)
            addProduct(s,product,1);
    }

    public static void storeCurentProducts(Set<Stock>stocks) {
        for(Stock s :stocks) {
            for (Map.Entry<Product, Integer> entry : s.getEl().getProducts().entrySet()) {
                writeProductInCSV(entry.getKey(), entry.getValue());
                insertProduct(entry.getKey(), entry.getValue(), s.getName());
            }
            for (Map.Entry<Product, Integer> entry : s.getPc().getProducts().entrySet()) {
                writeProductInCSV(entry.getKey(), entry.getValue());
                insertProduct(entry.getKey(), entry.getValue(), s.getName());
            }
        }
    }

    public static void writeProductInCSV(Product product, Integer nr) {
        if (product instanceof Laptop) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getColor(), product.getDistributor().getName(), ((Laptop) product).getProcesor(), Integer.toString(nr)};
            Singleton.getInstance().writeInCsv("Laptops.csv", data);
        }
        if (product instanceof Camera) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getColor(), product.getDistributor().getName(), Integer.toString(((Camera) product).getMegapixels()), Integer.toString(nr)};
            Singleton.getInstance().writeInCsv("Cameras.csv", data);
        }

        if (product instanceof Parfum) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getColor(), product.getDistributor().getName(), ((Parfum) product).getParfumType(), Integer.toString(nr)};
            Singleton.getInstance().writeInCsv("Parfums.csv", data);
        }
        if (product instanceof Shampoo) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getColor(), product.getDistributor().getName(), ((Shampoo) product).getHairType(), Integer.toString(nr)};
            Singleton.getInstance().writeInCsv("Shampoos.csv", data);
        }
    }

    public static List<Distributor> readDistributors() {
        return Singleton.getInstance().readDistributors("ReadDistributors.csv");
    }

    public static void addDistributorsFromCSV(ArrayList<Distributor> distributors, List<Distributor> distribList ){
        for(Distributor distributor :distribList)
            addDistributor(distributors, distributor);
    }

    public static void storeCurentDistributors(ArrayList<Distributor> distributors) {
        for(Distributor distributor :distributors) {
            writeDistributorInCSV(distributor);
            insertDistributor(distributor);
        }
    }


    public static void writeDistributorInCSV(Distributor distributor) {
        String[] data = {distributor.getName(), distributor.getAddress(), distributor.getPhoneNumber()};
        Singleton.getInstance().writeInCsv("Distributors.csv", data);
    }

    public static void actionCompleted(String name){
        String [] data = {name, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date())};
        Singleton.getInstance().writeInCsv("Actions.csv", data);
    }


    //etapa3
    public static void insertProduct(Product product, int quantity, String stockName) {
        if (product instanceof Laptop) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getColor(), product.getDistributor().getName(), ((Laptop) product).getProcesor(), Integer.toString(quantity), stockName};
            Database.getDatabaseInstance().insertProduct(data, "laptops");
        }
        if (product instanceof Camera) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getColor(), product.getDistributor().getName(), Integer.toString(((Camera) product).getMegapixels()), Integer.toString(quantity), stockName};
            Database.getDatabaseInstance().insertProduct(data, "cameras");
        }

        if (product instanceof Parfum) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getColor(), product.getDistributor().getName(), ((Parfum) product).getParfumType(), Integer.toString(quantity), stockName};
            Database.getDatabaseInstance().insertProduct(data, "parfums");
        }

        if (product instanceof Shampoo) {
            String[] data = {product.getName(), Integer.toString(product.getPrice()), product.getColor(), product.getDistributor().getName(), ((Shampoo) product).getHairType(), Integer.toString(quantity), stockName};
            Database.getDatabaseInstance().insertProduct(data, "shampoos");
        }
    }

    private static void insertDistributor(Distributor distributor) {
        String[] data = {distributor.getName(), distributor.getAddress(), distributor.getPhoneNumber()};
        Database.getDatabaseInstance().insertDistributor(data);
    }

    private static void deleteProduct(Product product) {
        if (product instanceof Laptop) {
            Database.getDatabaseInstance().deleteProduct(product.getName(), "laptops");
        }
        if (product instanceof Camera) {
            Database.getDatabaseInstance().deleteProduct(product.getName(), "cameras");
        }

        if (product instanceof Parfum) {
            Database.getDatabaseInstance().deleteProduct(product.getName(), "parfums");
        }

        if (product instanceof Shampoo) {
            Database.getDatabaseInstance().deleteProduct(product.getName(), "shampoos");
        }
    }

    private static void updatePrice(Product product, int newPrice) {
        if(product instanceof Parfum)
            Database.getDatabaseInstance().updateProductPrice("parfums", product.getName(), Integer.toString(newPrice));
        else if (product instanceof Shampoo)
            Database.getDatabaseInstance().updateProductPrice("shampoos", product.getName(), Integer.toString(newPrice));
        else if (product instanceof Laptop)
            Database.getDatabaseInstance().updateProductPrice("laptops", product.getName(), Integer.toString(newPrice));
        else if (product instanceof Camera)
            Database.getDatabaseInstance().updateProductPrice("cameras", product.getName(), Integer.toString(newPrice));
    }






    //etapa1

    public static void addProduct(Stock s, Product product, int numberOfProducts) {
        actionCompleted("addProduct");
        insertProduct(product, numberOfProducts,s.getName());
        writeProductInCSV(product, numberOfProducts);
        if(product instanceof Shampoo ||product instanceof Parfum)
            s.getPc().addProduct(product, numberOfProducts);
        else if(product instanceof Laptop ||product instanceof Camera)
            s.getEl().addProduct(product, numberOfProducts);
    }

    public static void addDistributor(ArrayList<Distributor> distributors, Distributor distributor) {
        actionCompleted("addDistributor");
        writeDistributorInCSV(distributor);
        insertDistributor(distributor);
        distributors.add(distributor);
    }

    public static void display(Set<Stock> stocks){
        actionCompleted("display");
        for(Stock s :stocks) {
            for (Map.Entry<Product, Integer> entry : s.getEl().getProducts().entrySet())
                System.out.println(entry.getKey());
            for (Map.Entry<Product, Integer> entry : s.getPc().getProducts().entrySet())
                System.out.println(entry.getKey());
        }
    }

    public static void removeProduct(Set<Stock> stocks, String stockName, String productName) {
        actionCompleted("removeProduct");
        for(Stock s :stocks)
            if(s.getName().equals(stockName))
            {   if (s.getPc().SearchProduct(productName))
                    s.getPc().removeProduct(productName);
                if (s.getEl().SearchProduct(productName))
                    s.getEl().removeProduct(productName);
            }
    }

    public static void modifyPrice(Set<Stock> stocks, String stockName, String productName, int newPrice) {
        actionCompleted("modifyPrice");
        for(Stock s :stocks)
            if(s.getName().equals(stockName))
            {   if (s.getPc().SearchProduct(productName))
                    for(Product product :s.getPc().getProducts().keySet())
                        if (product.getName().equals(productName)) {
                            product.setPrice(newPrice);
                            updatePrice(product, newPrice);
                        }
                if (s.getEl().SearchProduct(productName))
                    for(Product product :s.getEl().getProducts().keySet())
                        if (product.getName().equals(productName)) {
                            product.setPrice(newPrice);
                            updatePrice(product, newPrice);
                        }
            }

    }

    public static void checkPrice(Set<Stock> stocks, String stockName, String productName) {
        actionCompleted("checkPrice");
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
        actionCompleted("increasePrice");
        for(Stock s :stocks)
            if(s.getName().equals(stockName)) {
                s.getPc().increasePrice(percentage);
                s.getEl().increasePrice(percentage);
            }
    }

    public static void decreasePrice(Set<Stock> stocks, String stockName, int percentage) {
        actionCompleted("decreasePrice");
        for(Stock s :stocks)
            if(s.getName().equals(stockName)) {
                s.getPc().decreasePrice(percentage);
                s.getEl().decreasePrice(percentage);
            }
    }

    public static void mostExpensive(Set<Stock> stocks, String stockName) {
        actionCompleted("mostExpensive");
        for(Stock s :stocks)
            if(s.getName().equals(stockName)) {
                if (s.getPc().mostExpensive().getPrice() > s.getEl().mostExpensive().getPrice())
                    System.out.println(s.getPc().mostExpensive());
                else
                    System.out.println(s.getEl().mostExpensive());
            }
    }

    public static void addStock(Set<Stock> stocks) {
        actionCompleted("addStock");
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
            writeDistributorInCSV(productDistributor);
            Parfum parfum = new Parfum(productName, productPrice, productColor, productDistributor, parfumType);
            addProduct(stock, parfum , nr);
            writeProductInCSV(parfum, nr);
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
            writeDistributorInCSV(productDistributor);
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
            writeDistributorInCSV(productDistributor);
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
            writeDistributorInCSV(productDistributor);
            Camera camera = new Camera(productName, productPrice, productColor, productDistributor, megapixels);
            addProduct(stock, camera , nr);
        }

        stocks.add(stock);
    }

    public static void deleteStock(Set<Stock> stocks, String stockName) {
        actionCompleted("deleteStock");
        stocks.removeIf(s -> s.getName().equals(stockName));
    }

    public static void distributorProducts (Set<Stock> stocks, String distributorName) {
        actionCompleted("printDistributorProducts");
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
        actionCompleted("printParfums");
        for(Stock s :stocks) {
            for (Map.Entry<Product, Integer> entry : s.getPc().getProducts().entrySet())
                if(entry.getKey() instanceof Parfum)
                    System.out.println(entry.getKey());
        }
    }


}
