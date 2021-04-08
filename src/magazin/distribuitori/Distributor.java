package magazin.distribuitori;

public class Distributor {
    private String name;
    private String address;
    private String phoneNumber;

    public Distributor() {

    }

    public Distributor(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("Nume distribuitor: " + this.name);
        System.out.println();
        System.out.println("Adresa distribuitor: " + this.address);
        System.out.println();
        System.out.println("Numar de telefon distribuitor: " + this.phoneNumber);
        System.out.println();
    }

}
