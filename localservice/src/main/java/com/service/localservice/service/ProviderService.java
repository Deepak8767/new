package com.service.localservice.service;

import com.service.localservice.model.Provider;
import com.service.localservice.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    private final ProviderRepository repo;

    public ProviderService(ProviderRepository repo) { this.repo = repo; }

    public List<Provider> search(String type, String pincode) {
        if (type == null) return repo.findAll();
        if (pincode == null || pincode.isBlank()) return repo.findByType(type);
        return repo.findByTypeAndPincode(type, pincode);
    }

    public Provider save(Provider p) { return repo.save(p); }
    public java.util.Optional<Provider> findByEmail(String email) { return repo.findByEmail(email); }
    public java.util.Optional<Provider> findById(Long id) { return repo.findById(id); }
}
