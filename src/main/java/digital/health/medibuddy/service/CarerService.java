package digital.health.medibuddy.service;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

import digital.health.medibuddy.model.Carer;
import digital.health.medibuddy.repository.CarerRepository;

@Service
public class CarerService {
	private final CarerRepository carerRepository;
	public CarerService(CarerRepository carerRepository) {
		super();
		this.carerRepository = carerRepository;
	}

    public Carer addCarer(Carer carer) throws IOException {

    	return carerRepository.save(carer);
    }
    
    public Carer updateCarer(Long carerId, Carer carerDetails) {
        Carer carer = carerRepository.findByCarerId(carerId);
        if (carer != null) {
            if (carerDetails.getEmail() != null) {
                carer.setEmail(carerDetails.getEmail());
            }
            if (carerDetails.getUserId() != null) {
                carer.setUserId(carerDetails.getUserId());
            }
            if (carerDetails.getFirstName() != null) {
                carer.setFirstName(carerDetails.getFirstName());
            }
            if (carerDetails.getMiddleName() != null) {
                carer.setMiddleName(carerDetails.getMiddleName());
            }
            if (carerDetails.getLastName() != null) {
                carer.setLastName(carerDetails.getLastName());
            }
            if (carerDetails.getRelationship() != null) {
                carer.setRelationship(carerDetails.getRelationship());
            }
            if (carerDetails.getNotify() != null) {
                carer.setNotify(carerDetails.getNotify());
            }
            
            return carerRepository.save(carer);

 
        } else {
            throw new RuntimeException("Carer not found with ID: " + carerId);
        }
    }
    
    public void deleteCarer(Long carerId) {
        Carer carer= carerRepository.findByCarerId(carerId);
        if (carer != null) {
        	carerRepository.deleteById(carerId);
        } else {
            throw new RuntimeException("Carer not found with ID: " + carerId);
        }
    }
    

    public Carer findByCarerId(Long carerId) {
        return carerRepository.findByCarerId(carerId);
    }
    
    public List<Carer> findByUserId(Long userId) {
        return carerRepository.findByUserId(userId);
    }

}
