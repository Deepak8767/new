package com.service.localservice.controller;
import com.service.localservice.model.Booking;
import com.service.localservice.model.Provider;
import com.service.localservice.model.User;
import com.service.localservice.repository.ProviderRepository;
import com.service.localservice.repository.UserRepository;
import com.service.localservice.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class BookingController {
    private final BookingService bookingService;
    private final UserRepository userRepo;
    private final ProviderRepository providerRepo;

    public BookingController(BookingService bookingService, UserRepository userRepo, ProviderRepository providerRepo) {
        this.bookingService = bookingService;
        this.userRepo = userRepo;
        this.providerRepo = providerRepo;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        try {
            Long userId = Long.valueOf(body.get("userId").toString());
            Long providerId = Long.valueOf(body.get("providerId").toString());
            String notes = (String) body.getOrDefault("notes", "");

            User user = userRepo.findById(userId).orElse(null);
            Provider provider = providerRepo.findById(providerId).orElse(null);
            if (user == null || provider == null) return ResponseEntity.badRequest().body(Map.of("error", "invalid user or provider"));

            Booking b = new Booking(user, provider, LocalDateTime.now(), "PENDING", notes);
            Booking saved = bookingService.create(b);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            // Catch NullPointerException if userId or providerId is missing, or ClassCastException
            return ResponseEntity.badRequest().body(Map.of("error", "invalid payload"));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> forUser(@PathVariable Long userId) {
        var opt = userRepo.findById(userId);
        if (opt.isEmpty()) return ResponseEntity.status(404).body(Map.of("error", "user not found"));
        return ResponseEntity.ok(bookingService.forUser(opt.get()));
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<?> forProvider(@PathVariable Long providerId) {
        var opt = providerRepo.findById(providerId);
        if (opt.isEmpty()) return ResponseEntity.status(404).body(Map.of("error", "provider not found"));
        return ResponseEntity.ok(bookingService.forProvider(opt.get()));
    }

    // provider action: update booking status (ACCEPTED / DECLINED)
    @PostMapping("/provider/{providerId}/booking/{bookingId}/status")
    public ResponseEntity<?> updateBookingStatus(@PathVariable Long providerId, @PathVariable Long bookingId, @RequestBody Map<String, String> body) {
        var providerOpt = providerRepo.findById(providerId);
        if (providerOpt.isEmpty()) return ResponseEntity.status(404).body(Map.of("error", "provider not found"));
        var bookingOpt = bookingService.findById(bookingId);
        if (bookingOpt.isEmpty()) return ResponseEntity.status(404).body(Map.of("error", "booking not found"));
        var booking = bookingOpt.get();
        if (!booking.getProvider().getId().equals(providerId)) return ResponseEntity.status(403).body(Map.of("error", "not authorized"));
        String status = body.get("status");
        if (status == null) return ResponseEntity.badRequest().body(Map.of("error", "status required"));
        booking.setStatus(status);
        bookingService.create(booking);
        return ResponseEntity.ok(booking);
    }
}
