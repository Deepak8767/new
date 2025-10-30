package com.service.localservice.config;

import com.service.localservice.model.Provider;
import com.service.localservice.model.User;
import com.service.localservice.service.ProviderService;
import com.service.localservice.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final ProviderService providerService;
    private final UserService userService;

    public DataLoader(ProviderService providerService, UserService userService) {
        this.providerService = providerService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        providerService.save(new Provider("Raj Plumbers", "Plumber", "560001", 4.5, "raj@plumber.com", "rajpass", "9876543210"));
    var p1 = new Provider("Sunrise Electric", "Electrician", "560002", 4.2, "sun@electric.com", "sunpass", "9876543211");
    p1.setAvailability("Weekdays 9-6"); p1.setPricePerHour(400.0); providerService.save(p1);
    var p2 = new Provider("Quick Paints", "Painter", "560001", 4.0, "quick@paint.com", "quickpass", "9876543212");
    p2.setAvailability("Weekends"); p2.setPricePerHour(300.0); providerService.save(p2);
    var p3 = new Provider("Fix AC Co", "AC Repair", "560003", 4.6, "fix@ac.com", "fixpass", "9876543213");
    p3.setAvailability("Weekdays 10-4"); p3.setPricePerHour(500.0); providerService.save(p3);

        // example user - only create if not exists
        var existing = userService.findByEmail("demo@example.com");
        if (existing.isEmpty()) {
            userService.register(new User("Demo User", "demo@example.com", 30, "Other", "Demo address", "560001", "Bengaluru", "KA", "9999999999", "password"));
        }
    }
}
