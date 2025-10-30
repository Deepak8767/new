package com.service.localservice.repository;

import com.service.localservice.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Provider> findByType(String type);
    List<Provider> findByTypeAndPincode(String type, String pincode);
    Optional<Provider> findByEmail(String email);
}
