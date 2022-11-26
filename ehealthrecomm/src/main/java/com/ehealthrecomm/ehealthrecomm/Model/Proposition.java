package com.ehealthrecomm.ehealthrecomm.Model;


import javax.persistence.*;

@Entity
@Table(name="Proposition")
public class Proposition
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name="email")
    private String 	email;



    @Column(name="proposition")
    private String message;

    @Column(name="category")
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}