package com.mirrordust.telcodata.entity;

import java.io.Serializable;

public class Holder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String imei;

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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imei='" + imei + '\'' +
                '}';
    }
}
