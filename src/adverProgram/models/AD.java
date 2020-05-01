package adverProgram.models;

import java.util.Date;

public class AD {

    private String title;
    private String text;
    private int price;
    private Date createdDate;
    private Category category;
    private User author;


    public AD(String title, String text, int price, Date createdDate, Category category, User author) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.createdDate = createdDate;
        this.category = category;
        this.author = author;
    }

    public AD() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AD ad = (AD) o;

        if (price != ad.price) return false;
        if (title != null ? !title.equals(ad.title) : ad.title != null) return false;
        if (text != null ? !text.equals(ad.text) : ad.text != null) return false;
        if (createdDate != null ? !createdDate.equals(ad.createdDate) : ad.createdDate != null) return false;
        if (category != ad.category) return false;
        return author != null ? author.equals(ad.author) : ad.author == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AD{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", price=" + price +
                ", createdDate=" + createdDate +
                ", category=" + category +
                ", author=" + author +
                '}';
    }
}
