package org.com.sda.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findUser", query = "select u from User u where u.email = :email and u.password = :password"),
        @NamedQuery(name = "findExistingUser", query = "select u from User u where u.email = :email")
})
@Entity
@Table(name = "users")
public class User {
    @Id
    private int user_id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "total_amount")
    private double totalAmount;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
//    private Set<TripDetails> tripDetailsSet = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
//
//    public Set<TripDetails> getTripDetailsSet() {
//        return tripDetailsSet;
//    }
//
//    public void setTripDetailsSet(Set<TripDetails> tripDetailsSet) {
//        this.tripDetailsSet = tripDetailsSet;
//    }
}
