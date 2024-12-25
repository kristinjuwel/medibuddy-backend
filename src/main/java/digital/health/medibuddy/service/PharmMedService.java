package digital.health.medibuddy.service;

import digital.health.medibuddy.model.PharmMed;
import digital.health.medibuddy.model.Pharmacy;
import digital.health.medibuddy.repository.PharmMedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmMedService {
    private final PharmMedRepository pharmMedRepository;

    public PharmMedService(PharmMedRepository pharmMedRepository) {
        this.pharmMedRepository = pharmMedRepository;
    }

    public List<PharmMed> getAllPharmMeds() {
        return pharmMedRepository.findAll();
    }

    public List<PharmMed> getByPharmId(Long pharmId) {
        return pharmMedRepository.findByPharmId(pharmId);
    }

    public PharmMed getPharmMedById(Long pmedId) {
        PharmMed pharmacy = pharmMedRepository.findByPmedId(pmedId);
        if (pharmacy != null) {
            return pharmacy;
        } else {
            throw new RuntimeException("Medicine not found with ID: " + pmedId);
        }
    }

    public PharmMed createPharmMed(PharmMed pharmMed) {
        return pharmMedRepository.save(pharmMed);
    }

    public PharmMed updatePharmMed(Long id, PharmMed pharmMedDetails) {
        PharmMed existingPharmMed = getPharmMedById(id);

        if (pharmMedDetails.getName() != null) {
            existingPharmMed.setName(pharmMedDetails.getName());
        }
        if (pharmMedDetails.getDetails() != null) {
            existingPharmMed.setDetails(pharmMedDetails.getDetails());
        }
        if (pharmMedDetails.getQty() != null) {
            existingPharmMed.setQty(pharmMedDetails.getQty());
        }
        if (pharmMedDetails.getAvail() != null) {
            existingPharmMed.setAvail(pharmMedDetails.getAvail());
        }
        if (pharmMedDetails.getPharmId() != null) {
            existingPharmMed.setPharmId(pharmMedDetails.getPharmId());
        }

        return pharmMedRepository.save(existingPharmMed);
    }

    public void deletePharmMed(Long id) {
        PharmMed pharmacy = pharmMedRepository.findByPmedId(id);
        if (pharmacy != null) {
            pharmMedRepository.deleteById(id);
        } else {
            throw new RuntimeException("Medicine not found with ID: " + id);
        }
    }
}
