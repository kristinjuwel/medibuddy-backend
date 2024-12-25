package digital.health.medibuddy.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import digital.health.medibuddy.model.Carer;
import digital.health.medibuddy.service.CarerService;


@RestController
@RequestMapping("/carer")
@CrossOrigin
public class CarerController {
	private final CarerService carerService;
	
	@Autowired
	public CarerController(CarerService carerService) {
		this.carerService = carerService;
	}
	
	@PostMapping
    public Carer addCarer(@RequestBody Carer carer) throws IOException {
        return carerService.addCarer(carer);
    }

    @GetMapping("/{carerId}")
    public ResponseEntity<Carer> getCarerById(@PathVariable Long carerId) {
       	Carer carer = carerService.findByCarerId(carerId);
        if (carer != null) {
            return ResponseEntity.ok(carer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Carer>> getCarerByUserId(@PathVariable Long userId) {
        List<Carer> carers =  carerService.findByUserId(userId);
        return ResponseEntity.ok(carers);
    }
    

    @PutMapping("/update/{carerId}")
    public ResponseEntity<Carer> updateUser(@PathVariable Long carerId, @RequestBody Carer carerDetails) {
        try {
            Carer updatedCarer = carerService.updateCarer(carerId, carerDetails);
            return ResponseEntity.ok(updatedCarer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{carerId}")
    public ResponseEntity<String> deleteCarer(@PathVariable Long carerId) {
        try {
            carerService.deleteCarer(carerId);
            return ResponseEntity.ok("Carer deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

	
}
