package model.entity;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "users")
    private User user;
    @ManyToOne
    @JoinColumn(name = "books")
    private Book book;

    public Reservation() {
    }

    public Reservation(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public Reservation(int id, User user, Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
