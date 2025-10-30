package com.service.localservice.service;

import com.service.localservice.model.Booking;
import com.service.localservice.model.Provider;
import com.service.localservice.model.User;
import com.service.localservice.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository repo;

    public BookingService(BookingRepository repo) { this.repo = repo; }

    public Booking create(Booking b) { return repo.save(b); }

    public List<Booking> forUser(User u) { return repo.findByUser(u); }

    public List<Booking> forProvider(Provider p) { return repo.findByProvider(p); }
    public java.util.Optional<Booking> findById(Long id) { return repo.findById(id); }
}
