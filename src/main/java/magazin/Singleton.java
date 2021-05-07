package magazin;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import magazin.distribuitori.Distributor;
import magazin.produse.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Singleton {
    private static Singleton instance;

    private Singleton(){ }

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                //createInstance();
                createInstanceIfNull();
            }
        }
        return instance;
    }
    private static void createInstance(){
        instance = new Singleton();
    }
    private static void createInstanceIfNull(){
        if(instance == null){
            instance = new Singleton();
        }
    }

    public List<Product> readProducts(String file)
    {   List<Product> products = new ArrayList<>();

        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(0)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData){
                if(row[0].equals("Parfum")) {
                    Parfum parfum = new Parfum(row[1], Integer.parseInt(row[2]), row[3], new Distributor(row[4], row[5], row[6]),row[7]);
                    products.add(parfum);
                }
                if(row[0].equals("Shampoo")) {
                    Shampoo shampoo = new Shampoo(row[1], Integer.parseInt(row[2]), row[3], new Distributor(row[4], row[5], row[6]),row[7]);
                    products.add(shampoo);
                }
                if(row[0].equals("Laptop")) {
                    Laptop laptop = new Laptop(row[1], Integer.parseInt(row[2]), row[3], new Distributor(row[4], row[5], row[6]),row[7]);
                    products.add(laptop);
                }
                if(row[0].equals("Camera")) {
                    Camera camera = new Camera(row[1], Integer.parseInt(row[2]), row[3], new Distributor(row[4], row[5], row[6]),Integer.parseInt(row[7]));
                    products.add(camera);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Distributor> readDistributors(String file)
    {   List<Distributor> distributors = new ArrayList<>();

        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(0)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData)
                if (row[0].equals("Distributor")) {
                    Distributor distributor = new Distributor(row[1], row[2], row[3]);
                    distributors.add(distributor);
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return distributors;
    }

    public void writeInCsv(String filePath, String[] data) {
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            if (!file.exists())
                file.createNewFile();

            PrintWriter outputfile = new PrintWriter(new FileWriter(file, true));
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(data);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createNewFile(String filePath) {
        try {
            Writer fileWriter = new FileWriter(filePath, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}