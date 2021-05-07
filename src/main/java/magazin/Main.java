package magazin;

import magazin.categorii.Electronics;
import magazin.categorii.PersonalCare;
import magazin.distribuitori.Distributor;
import magazin.produse.*;
import magazin.stocuri.Stock;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        int i;
        Distributor distributor1 = new Distributor("EuroItalia", "Milano, Str 12", "0822222");
        Distributor distributor2 = new Distributor("RoDistrib", "Bucuresti, Str Dunarii", "0723456");
        ArrayList<Distributor> distributors = new ArrayList<>();
        distributors.add(distributor1);
        distributors.add(distributor2);
        Parfum parfum1 = new Parfum("Sauvage", 100, "blue", distributor1, "fresh");
        Shampoo shampoo1 = new Shampoo("Gerovi", 50, "white", distributor1, "uscat");
        Laptop laptop1 = new Laptop("ddd",1000,"black", distributor2,"dd");
        Camera camera1 = new Camera("Samsung", 500, "blue", distributor2, 5);
        Map<Product, Integer> map1 = new HashMap<>();
        map1.put(parfum1,5);
        map1.put(shampoo1,10);
        PersonalCare pc = new PersonalCare(map1, "Produsele trebuiesc depozitate corespunzator");
        Map<Product, Integer> map2 = new HashMap<>();
        map2.put(laptop1,5);
        map2.put(camera1,10);
        Electronics el = new Electronics(map2);

        Stock stock1 = new Stock("Stock1", pc, el);
        Scanner in=new Scanner(System.in);

        List<Stock> abc = new ArrayList<>();
        abc.add(stock1);
        Set<Stock> stocks = new TreeSet<>(abc);

        Singleton.getInstance().createNewFile("Laptops.csv");
        Singleton.getInstance().createNewFile("Cameras.csv");
        Singleton.getInstance().createNewFile("Parfums.csv");
        Singleton.getInstance().createNewFile("Shampoos.csv");
        Singleton.getInstance().createNewFile("Actions.csv");
        Singleton.getInstance().createNewFile("Distributors.csv");

        Services.writeProductsInCSV(stocks);
        Services.addProductsFromCSV(stock1, Services.readProducts());
        Services.writeDistributorsInCSV(distributors);
        Services.addDistributorsFromCSV(distributors, Services.readDistributors());

        Services.display();
        System.out.println("Cate servicii doriti?");
        int nr_services=in.nextInt();

        for(i=0; i<nr_services; i++)
        {
            System.out.println("Numarul serviciului dorit:");

            int number = in.nextInt();


            if (number== 1)
            {   System.out.println("Care este numele stocului?");
                String stockName=in.next();
                System.out.println("Care este numarul de produse adaugate in stoc?");
                int numberOfProducts=in.nextInt();
                System.out.println("Care este tipul produsului? 1-shampoo 2-parfum 3-camera 4-laptop ");
                int x =in.nextInt();
                System.out.println("Care este numele produsului?");
                String productName=in.next();
                System.out.println("Care este pretul produsului?");
                int productPrice=in.nextInt();
                System.out.println("Care este culoarea produsului?");
                String productColor=in.next();
                System.out.println("Care este numele distribuitorului?");
                String distributorName = in.next();
                System.out.println("Care este adresa distribuitorului?");
                String distributorAddress = in.next();
                System.out.println("Care este numarul de telefon al distribuitorului?");
                String distributorPhoneNumber = in.next();
                Distributor productDistributor = new Distributor(distributorName, distributorAddress, distributorPhoneNumber);

                if(x==1)
                {   System.out.println("Care este tipul parului");
                    String hairType=in.next();
                    for(Stock s :stocks)
                        if(s.getName().equals(stockName))
                    Services.addProduct(s, new Shampoo(productName, productPrice, productColor, productDistributor, hairType), numberOfProducts);
                }

                if(x==2) {
                    System.out.println("Care este tipul parfumului?");
                    String parfumType=in.next();
                    for(Stock s :stocks)
                        if(s.getName().equals(stockName))
                    Services.addProduct(s, new Parfum(productName, productPrice, productColor, productDistributor, parfumType), numberOfProducts);
                }

                if(x==3) {
                    System.out.println("Care este numarul de megapixeli?");
                    int megapixels=in.nextInt();
                    for(Stock s :stocks)
                        if(s.getName().equals(stockName))
                    Services.addProduct(s, new Camera(productName, productPrice, productColor, productDistributor, megapixels), numberOfProducts);
                }
                if(x==4) {
                    System.out.println("Care este tipul procesorului?");
                    String procesorType=in.next();
                    for(Stock s :stocks)
                        if(s.getName().equals(stockName))
                    Services.addProduct(s, new Laptop(productName, productPrice, productColor, productDistributor, procesorType), numberOfProducts);
                }
                Services.display(stocks);

            }
            else if (number==2) {
                System.out.println("Care este numele produsului?");
                String productName=in.next();
                System.out.println("Care este numele stocului?");
                String stockName=in.next();
                Services.removeProduct(stocks, stockName, productName);
                Services.display(stocks);
            }

            else if (number==3) {
                System.out.println("Care este numele produsului?");
                String productName=in.next();
                System.out.println("Care este numele stocului?");
                String stockName=in.next();
                Services.checkPrice(stocks, stockName, productName);
                System.out.println("Care este noul pret?");
                int newPrice=in.nextInt();
                Services.modifyPrice(stocks,stockName,productName,newPrice);
            }

            else if(number==4) {
                Services.display(stocks);
            }

            else if(number==5) {
                System.out.println("Care este numele stocului?");
                String stockName=in.next();
                System.out.println("Care este procentajul % cu care vreti sa mariti pretul?");
                int percentage=in.nextInt();
                Services.increasePrice(stocks, stockName, percentage);
                Services.display(stocks);
            }

            else if(number==6) {
                System.out.println("Care este numele stocului?");
                String stockName=in.next();
                System.out.println("Care este procentajul % cu care vreti sa mariti pretul?");
                int percentage=in.nextInt();
                Services.decreasePrice(stocks, stockName, percentage);
            }

            else if(number==7) {
                System.out.println("Care este numele produsului?");
                String productName=in.next();
                System.out.println("Care este numele stocului?");
                String stockName=in.next();
                Services.checkPrice(stocks, stockName, productName);
            }

            else if(number==8) {
                Services.addStock(stocks);
            }

            else if(number==9) {
                System.out.println("Care este numele stocului pe care doriti sa il stergeti?");
                String stockName=in.next();
                Services.deleteStock(stocks, stockName);
            }
            else if(number==10) {
                System.out.println("Care este numele stocului?");
                String stockName=in.next();
                Services.mostExpensive(stocks, stockName);
            }

            else if(number==11) {
                Services.printParfums(stocks);
            }

            else if(number==12) {
                System.out.println("Care este numele distribuitorului?");
                String distributorName=in.next();
                Services.distributorProducts (stocks, distributorName);
            }


        }

    }
}
