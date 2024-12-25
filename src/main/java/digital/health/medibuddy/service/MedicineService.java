package digital.health.medibuddy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import digital.health.medibuddy.model.Medicine;
import digital.health.medibuddy.repository.MedicineRepository;

@Service
public class MedicineService {
	private final MedicineRepository medRepository;

	public MedicineService(MedicineRepository medRepository) {
		super();
		this.medRepository = medRepository;
	}
	public Medicine addMed(Medicine med, List<MultipartFile> files) throws IOException {
	    if (files != null && !files.isEmpty()) {
	        List<byte[]> fileDataList = new ArrayList<>();
	        List<String> fileNamesList = new ArrayList<>();
	        List<String> fileTypesList = new ArrayList<>();

	        for (MultipartFile file : files) {
	            if (file != null && file.getSize() > 0) {
	                fileDataList.add(file.getBytes());
	                fileNamesList.add(file.getOriginalFilename());
	                fileTypesList.add(file.getContentType());
	            }
	        }

	        med.setFiles(fileDataList);
	        med.setAttachments(String.join(",", fileNamesList));
	        med.setFileType(String.join(",", fileTypesList)); 
	    }

	    return medRepository.save(med);
	}
	
	public Medicine updateMed(Long medId, Medicine updatedMed, List<MultipartFile> files) throws IOException {
	    Medicine existingMed = medRepository.findById(medId)
	            .orElseThrow(() -> new IllegalArgumentException("Medicine with ID " + medId + " not found"));

	    if (updatedMed.getName() != null) existingMed.setName(updatedMed.getName());
	    if (updatedMed.getDescription() != null) existingMed.setDescription(updatedMed.getDescription());
	    if (updatedMed.getInstructions() != null) existingMed.setInstructions(updatedMed.getInstructions());
	    if (updatedMed.getDose() != null) existingMed.setDose(updatedMed.getDose());
	    if (updatedMed.getRequiredQty() != null) existingMed.setRequiredQty(updatedMed.getRequiredQty());
	    if (updatedMed.getInitialQty() != null) existingMed.setInitialQty(updatedMed.getInitialQty());
	    if (updatedMed.getCurrentQty() != null) existingMed.setCurrentQty(updatedMed.getCurrentQty());
	    if (updatedMed.getUnit() != null) existingMed.setUnit(updatedMed.getUnit());

	    if (files != null && !files.isEmpty()) {
	        List<byte[]> fileDataList = new ArrayList<>();
	        List<String> fileNamesList = new ArrayList<>();
	        List<String> fileTypesList = new ArrayList<>();

	        for (MultipartFile file : files) {
	            if (file != null && file.getSize() > 0) {
	                fileDataList.add(file.getBytes());
	                fileNamesList.add(file.getOriginalFilename());
	                fileTypesList.add(file.getContentType());
	            }
	        }

	        existingMed.setFiles(fileDataList);
	        existingMed.setAttachments(String.join(",", fileNamesList));
	        existingMed.setFileType(String.join(",", fileTypesList)); 
	    }

	    return medRepository.save(existingMed);
	}

    
	public void deleteMed(Long medId) {
        Medicine existingMed = medRepository.findByMedId(medId);
        if (existingMed != null) {
    	    medRepository.delete(existingMed);
        } else {
            throw new RuntimeException("Medicine not found with ID: " + medId);
        }
	}

    public Medicine findByMedId(Long medId) {
        return medRepository.findByMedId(medId);
    }
    
    public List<Medicine> findByUserId(Long userId) {
        return medRepository.findByUserId(userId);
    }
    
    
}
