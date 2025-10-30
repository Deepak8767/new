package com.service.localservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String pincode;
    private Double rating;
    private String email;
    private String password;
    private String mobile;
    private String availability; // e.g., "Weekdays 9-6" or "Weekends"
    private String address;
    private Double pricePerHour;
    private String city;
    private String district;
    private String state;

    public Provider() {}

    public Provider(String name, String type, String pincode, Double rating) {
        this.name = name;
        this.type = type;
        this.pincode = pincode;
        this.rating = rating;
    }

    public Provider(String name, String type, String pincode, Double rating, String email, String password, String mobile) {
        this.name = name;
        this.type = type;
        this.pincode = pincode;
        this.rating = rating;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
    }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    public Double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(Double pricePerHour) { this.pricePerHour = pricePerHour; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
}
