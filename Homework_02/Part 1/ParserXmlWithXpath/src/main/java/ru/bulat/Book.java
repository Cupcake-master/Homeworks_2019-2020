package ru.bulat;

class Book{
    private String title, author, category, cover, lang;
    private float price;
    private int year;

    public Book(String category, String cover, String lang){
        this.category = category;
        this.cover = cover;
        this.lang = lang;
    }

    public String getTitle() {
        return title;
    }

    public String getLang() {
        return lang;
    }

    public String getCategory() {
        return category;
    }

    public String getCover() {
        return cover;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
