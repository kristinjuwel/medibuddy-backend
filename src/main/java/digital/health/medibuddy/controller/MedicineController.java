package digital.health.medibuddy.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import digital.health.medibuddy.model.Medicine;
import digital.health.medibuddy.service.MedicineService;

@RestController
@RequestMapping("/med")
@CrossOrigin
public class MedicineController {
	private final MedicineService medService;
	
	@Autowired
	public MedicineController(MedicineService medService) {
		this.medService = medService;
	}
	
    @PostMapping
    public ResponseEntity<Medicine> addMedicine(
            @RequestPart("body") Medicine med,
            @RequestPart("files") List<MultipartFile> files) throws IOException {
        Medicine savedMed = medService.addMed(med, files);
        return ResponseEntity.ok(savedMed);
    }
    
    @GetMapping("/{medId}")
    public ResponseEntity<Medicine> getMedById(@PathVariable Long medId) {
       	Medicine med = medService.findByMedId(medId);
        if (med != null) {
            return ResponseEntity.ok(med);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Medicine>> getMedByUserId(@PathVariable Long userId) {
        List<Medicine> meds =  medService.findByUserId(userId);
        return ResponseEntity.ok(meds);
    }
    
    
    @PutMapping("/{medId}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long medId,
                                                           @RequestPart("body") Medicine med,
                                                           @RequestPart("files") List<MultipartFile> files) throws IOException {
    	Medicine savedMed = medService.updateMed(medId, med, files);
        return ResponseEntity.ok(savedMed);
    }
    
    @DeleteMapping("/delete/{medId}")
    public ResponseEntity<String> deleteMed(@PathVariable Long medId) {
        try {
            medService.deleteMed(medId);
            return ResponseEntity.ok("Medicine deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
	
}
