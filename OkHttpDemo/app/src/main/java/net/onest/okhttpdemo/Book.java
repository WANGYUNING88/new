package net.onest.okhttpdemo;

import java.util.List;

/**
 * Created by zyl on 2018/4/26.
 */

public class Book {
    private String bookName;
    private String bookPrice;
    private String bookDetails;
    private List<String> bookImages;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(String bookDetails) {
        this.bookDetails = bookDetails;
    }

    public List<String> getBookImages() {
        return bookImages;
    }

    public void setBookImages(List<String> bookImages) {
        this.bookImages = bookImages;
    }
}
