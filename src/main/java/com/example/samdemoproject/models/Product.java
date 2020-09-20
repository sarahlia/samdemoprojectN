package com.example.samdemoproject.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

//    @OneToOne
//    private User user;

    @Column
    private double price;

    @Column(columnDefinition = "TEXT")
    private String img;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    //empty constructor
    public Product() {

    }
    //create--old
    public Product(String name, String description, double price, String img) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    //create--the minimum
    public Product(String name, String description, double price, User user) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.user = user;
    }

    //create
    public Product(String name, String description, double price, String img, User user) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.user = user;
    }

    //read--old
    public Product(long id, String name, String description, double price, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    //read--the minimum
    public Product(long id, String name, String description, double price, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.user = user;
    }

    //read
    public Product(long id, String name, String description, double price, String img, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
