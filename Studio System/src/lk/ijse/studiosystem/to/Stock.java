package lk.ijse.studiosystem.to;

public class Stock {
    private String stock_id;
    private String date;

    public Stock() {
    }

    public Stock(String stock_id, String date) {
        this.stock_id = stock_id;
        this.date = date;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
