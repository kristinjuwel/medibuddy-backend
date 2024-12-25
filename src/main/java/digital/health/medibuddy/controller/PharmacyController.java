package digital.health.medibuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import digital.health.medibuddy.model.Pharmacy;
import digital.health.medibuddy.service.PharmacyService;

@RestController
@RequestMapping("/pharmacy")
@CrossOrigin
public class PharmacyController {
	private final PharmacyService pharmacyService;
	
	@Autowired
	public PharmacyController(PharmacyService pharmacyService) {
		this.pharmacyService = pharmacyService;
	}
	
	@PostMapping
    public ResponseEntity<Pharmacy> createPharmacy(@RequestBody Pharmacy pharmacy) {
        Pharmacy savedPharmacy = pharmacyService.savePharmacy(pharmacy);
        return ResponseEntity.ok(savedPharmacy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable Long id) {
        Pharmacy pharmacy = pharmacyService.getPharmacyById(id);
        return ResponseEntity.ok(pharmacy);
    }

    @GetMapping
    public ResponseEntity<List<Pharmacy>> getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
        return ResponseEntity.ok(pharmacies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pharmacy> updatePharmacy(@PathVariable Long id, @RequestBody Pharmacy pharmacy) {
        Pharmacy updatedPharmacy = pharmacyService.updatePharmacy(id, pharmacy);
        return ResponseEntity.ok(updatedPharmacy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacy(@PathVariable Long id) {
        pharmacyService.deletePharmacy(id);
        return ResponseEntity.noContent().build();
    }
}
