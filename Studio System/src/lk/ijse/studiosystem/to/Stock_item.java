package lk.ijse.studiosystem.to;

public class Stock_item {
    private String item_id;
    private String stock_id;
    private int stock_item_qty;

    public Stock_item() {
    }

    public Stock_item(String item_id, String stock_id, int stock_item_qty) {
        this.item_id = item_id;
        this.stock_id = stock_id;
        this.stock_item_qty = stock_item_qty;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public int getStock_item_qty() {
        return stock_item_qty;
    }

    public void setStock_item_qty(int stock_item_qty) {
        this.stock_item_qty = stock_item_qty;
    }
}
