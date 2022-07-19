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
    @JoinColumn(name = "categories")
    private Category category;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

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
        return "Book" +
                "id: " + id +
                "title: " + title + '\'' +
                "description: " + description + '\'' +
                "isbn: " + isbn +
                "photo: " + photo + '\'' +
                "numberOfPages: " + numberOfPages +
                "category: " + category;
    }
}
