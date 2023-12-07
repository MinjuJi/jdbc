package jdbc2prac;

import java.util.Date;

public class Product {
    private int no;
    private String name;
    private String maker;
    private int price;
    private int discount;
    private int stock;
    private String soldOut;
    private Date createDate;
    private Date updateDate;

    public Product(int no, String name, String maker, int price, int discount, int stock) {
        this.no = no;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
    }

    public Product(int no, String name, String maker, int price, int discount, int stock, String soldOut,
                   Date createDate, Date updateDate) {
        this.no = no;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.soldOut = soldOut;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getMaker() {
        return maker;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public int getStock() {
        return stock;
    }

    public String getSoldOut() {
        return soldOut;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
