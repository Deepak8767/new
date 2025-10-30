package com.service.localservice.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateBookingStatusRequest {
    @NotBlank(message = "status is required")
    @Pattern(regexp = "ACCEPTED|DECLINED", message = "status must be either ACCEPTED or DECLINED")
    private String status;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}