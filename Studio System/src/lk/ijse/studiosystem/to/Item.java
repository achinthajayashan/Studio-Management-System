package lk.ijse.studiosystem.to;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Item extends ArrayList<CartDetail> {
    private String item_ID;
    private String item_name;
    private Double unit_price;

    private int qty_on_hnd;

    private String supplier_ID;

    public Item() {
    }

    public Item(String item_ID, String item_name, Double unit_price, int qty_on_hnd, String supplier_ID) {
        this.item_ID = item_ID;
        this.item_name = item_name;
        this.unit_price = unit_price;
        this.qty_on_hnd=qty_on_hnd;
        this.supplier_ID=supplier_ID;
    }

    public Item(String code, String description, double unitPrice, int qty) {
        this.item_ID=code;
        this.item_name=description;
        this.unit_price=unitPrice;
        this.qty_on_hnd=qty;
    }

    public String getItem_ID() {
        return item_ID;
    }

    public void setItem_ID(String item_ID) {
        this.item_ID = item_ID;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQty_on_hnd() {
        return qty_on_hnd;
    }

    public void setQty_on_hnd(int qty_on_hnd) {
        this.qty_on_hnd = qty_on_hnd;
    }

    public String getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    @Override
    public Stream<CartDetail> stream() {
        return super.stream();
    }
}
