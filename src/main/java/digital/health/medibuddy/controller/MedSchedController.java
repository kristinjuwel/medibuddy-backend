package digital.health.medibuddy.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import digital.health.medibuddy.model.MedSched;
import digital.health.medibuddy.service.MedSchedService;
import jakarta.mail.MessagingException;


@RestController
@RequestMapping("/sched")
@CrossOrigin
public class MedSchedController {
	private final MedSchedService medSchedService;
	
	@Autowired
	public MedSchedController(MedSchedService medSchedService) {
		this.medSchedService = medSchedService;
	}
	
	@PostMapping
    public MedSched addSched(@RequestBody MedSched medSched) throws IOException {
        return medSchedService.addMedSched(medSched);
    }
	
    @GetMapping("/{schedId}")
    public ResponseEntity<MedSched> getMedSchedById(@PathVariable Long schedId) {
       	MedSched medSched = medSchedService.findBySchedId(schedId);
        if (medSched != null) {
            return ResponseEntity.ok(medSched);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    

    @GetMapping("/med/{medId}")
    public ResponseEntity<List<MedSched>> getMedSchedByMedId(@PathVariable Long medId) {
        List<MedSched> sched =  medSchedService.findByMedId(medId);
        return ResponseEntity.ok(sched);
    }
    

    @PutMapping("/update/{schedId}")
    public ResponseEntity<MedSched> updateUser(@PathVariable Long schedId, @RequestBody MedSched schedDetails) {
        try {
            MedSched updated = medSchedService.updateMedSched(schedId, schedDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
	
	@PutMapping("/check-taken/{schedId}/{userId}")
	public ResponseEntity<String> checkIfTaken(
	        @PathVariable Long schedId,
	        @PathVariable Long userId,
	        @RequestParam(defaultValue = "false") boolean isTaken,
	        @RequestParam(defaultValue = "") String qtyTaken,
	        @RequestParam(defaultValue = "") String action
	) {
	    try {
	        medSchedService.checkIfTaken(schedId, userId, isTaken, qtyTaken, action);
	        return ResponseEntity.ok("Schedule updated successfully.");
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (MessagingException | IOException e) {
	        return ResponseEntity.status(500).body("Error occurred while processing the request: " + e.getMessage());
	    }
	}
	
    @DeleteMapping("/delete/{schedId}")
    public ResponseEntity<String> deleteSched(@PathVariable Long schedId) {
        try {
            medSchedService.deleteMedSched(schedId);
            return ResponseEntity.ok("Schedule deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
