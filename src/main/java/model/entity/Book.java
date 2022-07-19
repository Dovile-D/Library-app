package model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "photo")
    private String photo;
    @Column(name = "pages")
    private int numberOfPages;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Book(int id, String title, String description, String isbn, String photo, int numberOfPages,
                Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.photo = photo;
        this.numberOfPages = numberOfPages;
        this.category = category;
    }

    public Book(String title, String description, String isbn, String photo, int numberOfPages,
                Category category) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.photo = photo;
        this.numberOfPages = numberOfPages;
        this.category = category;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "\n__________________________________________________\n" +
                "BOOK id: " + id +
                "\ntitle: " + title + '\'' +
                "\ndescription: " + description + '\'' +
                "\nisbn: " + isbn +
                "\nphoto: " + photo + '\'' +
                "\nnumber of pages: " + numberOfPages +
                "\ncategory: " + category;
    }
}
