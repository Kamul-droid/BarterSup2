package com.jee.bartersup.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Post")
public class Post  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Integer id;

    @Column(name = "title",nullable = false)
    private String title;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @Column (name = "price",nullable = false)
    private double price;


    @Column(name = "description", nullable = false,length = 2000)
    private String description;

    @Column(name = "created_at", nullable = false)
    private Date date;

    @OneToOne(cascade = CascadeType.MERGE )
    @JoinColumn (name = "image_id", nullable = true)
    private Image image;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post(Integer id, String title, Category type, double price,  String description, Date date, User user) {
        this.id = id;
        this.title = title;
        this.category = type;
        this.price = price;
        this.description = description;
        this.date = date;
        this.user = user;
    }

    public Post() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category type) {
        this.category = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", User='" + user.getUsername() + '\'' +
                ", title='" + title + '\'' +
                ", type='" + category + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
