package digital.health.medibuddy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import digital.health.medibuddy.model.Pharmacy;
import digital.health.medibuddy.repository.PharmacyRepository;

@Service
public class PharmacyService {
	private final PharmacyRepository pharmacyRepository;

	public PharmacyService(PharmacyRepository pharmacyRepository) {
		this.pharmacyRepository = pharmacyRepository;
	}
	
	public Pharmacy savePharmacy(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }
	
    public Pharmacy getPharmacyById(Long pharmId) {
        Pharmacy pharmacy = pharmacyRepository.findByPharmId(pharmId);
	    if (pharmacy != null) {
	    	return pharmacy;
	    } else {
	        throw new RuntimeException("Pharmacy not found with ID: " + pharmId);
	    }
     }
	
	public List<Pharmacy> getAllPharmacies() {
	    return pharmacyRepository.findAll();
	}
	
	public void deletePharmacy(Long pharmId) {
        Pharmacy pharmacy = pharmacyRepository.findByPharmId(pharmId);
        if (pharmacy != null) {
        	pharmacyRepository.deleteById(pharmId);
        } else {
            throw new RuntimeException("Pharmacy not found with ID: " + pharmId);
        }
	}
	
	public Pharmacy updatePharmacy(Long pharmId, Pharmacy updatedPharmacy) {
	    Pharmacy existingPharmacy = pharmacyRepository.findByPharmId(pharmId);

	    if (updatedPharmacy.getName() != null) {
	        existingPharmacy.setName(updatedPharmacy.getName());
	    }

	    if (updatedPharmacy.getAddress() != null) {
	        existingPharmacy.setAddress(updatedPharmacy.getAddress());
	    }

	    return pharmacyRepository.save(existingPharmacy);
	}
	
}
