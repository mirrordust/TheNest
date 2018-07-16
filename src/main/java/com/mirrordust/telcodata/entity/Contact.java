package com.mirrordust.telcodata.entity;

import java.io.Serializable;

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String email;
    private Integer type;  //0: ordinary recipient, 1: CC, 2: Bcc, others: for testing

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
