package com.service.localservice.repository;

import com.service.localservice.model.Booking;
import com.service.localservice.model.User;
import com.service.localservice.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByProvider(Provider provider);
}
