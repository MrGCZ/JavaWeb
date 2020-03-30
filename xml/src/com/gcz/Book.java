package com.gcz;


public class Book {

    private String sn;
    private String name;
    private Double price;
    private String author;

    public String getSn() {
        return sn;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Book(String sn, String name, Double price, String author) {
        this.sn = sn;
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }


}
