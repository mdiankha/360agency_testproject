package com.agency.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(length = 32, unique= true, nullable = false)
    private String vin;

    private String make;
    private String dealerName;
    private String model;
    private int year;
    


    private long registeredMobile;
    private double price;
    private String postingDate;
    @Column(length = 32, columnDefinition = "varchar(32) default 'DRAFT'",nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status= StatusEnum.DRAFT;

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRegisteredMobile() {
        return registeredMobile;
    }

    public void setRegisteredMobile(long registeredMobile) {
        this.registeredMobile = registeredMobile;
    }
    public enum StatusEnum {
        PUBLISHED, DRAFT;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", dealerName='" + dealerName + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", registeredMobile=" + registeredMobile +
                ", price=" + price +
                ", postingDate='" + postingDate + '\'' +
                '}';
    }
}