package com.service.localservice.controller.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class UpdateSettingsRequest {
    private String availability;

    @PositiveOrZero(message = "Price per hour must be a positive number or zero")
    private Double pricePerHour;

    // Getters and Setters
    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    public Double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(Double pricePerHour) { this.pricePerHour = pricePerHour; }
}