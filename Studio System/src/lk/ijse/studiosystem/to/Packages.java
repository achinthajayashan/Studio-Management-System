package lk.ijse.studiosystem.to;

public class Packages {
    private String package_ID;
    private String package_name;
    private double price;

    public Packages() {
    }

    public Packages(String package_ID, String package_name, double price) {
        this.package_ID = package_ID;
        this.package_name = package_name;
        this.price = price;
    }

    public String getPackage_ID() {
        return package_ID;
    }

    public void setPackage_ID(String package_ID) {
        this.package_ID = package_ID;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
