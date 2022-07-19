package model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "isAdmin")
    private boolean isAdmin;
    @Column(name = "password")
    private String password;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public User(int id, String name, String email, boolean isAdmin, String password, Reservation reservation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
        this.password = password;
        this.reservation = reservation;
    }

    public User(String name, String email, boolean isAdmin, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String admin, String s, String admin1) {
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Reservation getReservations() {
        return reservation;
    }

    public void setReservations(Reservation reservations) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", password='" + password + '\'' +
                '}';
    }
}
