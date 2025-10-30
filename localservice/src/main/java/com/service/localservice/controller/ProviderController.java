package com.service.localservice.controller;

import com.service.localservice.model.Provider;
import com.service.localservice.service.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/providers")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class ProviderController {
    private final ProviderService service;

    public ProviderController(ProviderService service) { this.service = service; }

    @GetMapping
    public List<Provider> search(@RequestParam(required = false) String type, @RequestParam(required = false) String pincode) {
        return service.search(type, pincode);
    }

    @PostMapping
    public Provider create(@RequestBody Provider p) {
        return service.save(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        var opt = service.findById(id);
        if (opt.isEmpty()) return ResponseEntity.status(404).body(Map.of("error", "provider not found"));
        return ResponseEntity.ok(opt.get());
    }

    // update provider settings (availability, price)
    @PostMapping("/{providerId}/settings")
    public ResponseEntity<?> updateSettings(@PathVariable Long providerId, @RequestBody Map<String, Object> body) {
        var opt = service.findById(providerId);
        if (opt.isEmpty()) return ResponseEntity.status(404).body(Map.of("error", "provider not found"));
        var p = opt.get();
        if (body.containsKey("availability")) p.setAvailability((String) body.get("availability"));
        if (body.containsKey("pricePerHour")) p.setPricePerHour(Double.valueOf(body.get("pricePerHour").toString()));
        service.save(p);
        return ResponseEntity.ok(p);
    }
}
