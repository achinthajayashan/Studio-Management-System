package lk.ijse.studiosystem.to;

public class Suplier {
    private String supplier_ID;
    private String supplier_name;
    private String address;
    private int contact;

    public Suplier() {
    }

    public Suplier(String supplier_ID, String supplier_name, String address, int contact) {
        this.supplier_ID = supplier_ID;
        this.supplier_name = supplier_name;
        this.address = address;
        this.contact = contact;
    }

    public String getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }
}
