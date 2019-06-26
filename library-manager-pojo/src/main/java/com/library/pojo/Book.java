package com.library.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private Integer bookId;

    private String name;

    private String author;

    private String publish;

    private String isbn;

    private String introduction;

    private String language;

    private BigDecimal price;

    private Date pubdate;

    private Integer classId;

    private Integer pressmark;

    private Integer state;
    
    

    public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Book(Integer bookId, String name, String author, String publish, String isbn, String introduction,
			String language, BigDecimal price, Date pubdate, Integer classId, Integer pressmark, Integer state) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.publish = publish;
		this.isbn = isbn;
		this.introduction = introduction;
		this.language = language;
		this.price = price;
		this.pubdate = pubdate;
		this.classId = classId;
		this.pressmark = pressmark;
		this.state = state;
	}



	public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getPressmark() {
        return pressmark;
    }

    public void setPressmark(Integer pressmark) {
        this.pressmark = pressmark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", author=" + author + ", publish=" + publish + ", isbn="
				+ isbn + ", introduction=" + introduction + ", language=" + language + ", price=" + price + ", pubdate="
				+ pubdate + ", classId=" + classId + ", pressmark=" + pressmark + ", state=" + state + "]";
	}
    
    
}