package lk.ijse.studiosystem.to;

public class Suplier_stock {
    private String suplier_id;
    private String stock_id;
    private int suplier_stock_qty;

    public Suplier_stock() {
    }

    public Suplier_stock(String suplier_id, String stock_id, int suplier_stock_qty) {
        this.suplier_id = suplier_id;
        this.stock_id = stock_id;
        this.suplier_stock_qty = suplier_stock_qty;
    }

    public String getSuplier_id() {
        return suplier_id;
    }

    public void setSuplier_id(String suplier_id) {
        this.suplier_id = suplier_id;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public int getSuplier_stock_qty() {
        return suplier_stock_qty;
    }

    public void setSuplier_stock_qty(int suplier_stock_qty) {
        this.suplier_stock_qty = suplier_stock_qty;
    }
}
