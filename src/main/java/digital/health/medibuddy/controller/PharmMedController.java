package digital.health.medibuddy.controller;

import digital.health.medibuddy.model.PharmMed;
import digital.health.medibuddy.service.PharmMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmed")
@CrossOrigin
public class PharmMedController {
    private final PharmMedService pharmMedService;

    @Autowired
    public PharmMedController(PharmMedService pharmMedService) {
        this.pharmMedService = pharmMedService;
    }

    @GetMapping
    public List<PharmMed> getAllPharmMeds() {
        return pharmMedService.getAllPharmMeds();
    }

    @GetMapping("/pharm/{pharmId}")
    public List<PharmMed> getByPharmacy(@PathVariable Long pharmId) {
        return pharmMedService.getByPharmId(pharmId);
    }

    @GetMapping("/{id}")
    public PharmMed getPharmMedById(@PathVariable Long id) {
        return pharmMedService.getPharmMedById(id);
    }

    @PostMapping
    public ResponseEntity<PharmMed> createPharmMed(@RequestBody PharmMed pharmMed) {
        PharmMed createdPharmMed = pharmMedService.createPharmMed(pharmMed);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPharmMed);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PharmMed> updatePharmMed(@PathVariable Long id, @RequestBody PharmMed pharmMedDetails) {
        PharmMed updatedPharmMed = pharmMedService.updatePharmMed(id, pharmMedDetails);
        return ResponseEntity.ok(updatedPharmMed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmMed(@PathVariable Long id) {
        pharmMedService.deletePharmMed(id);
        return ResponseEntity.noContent().build();
    }
}
