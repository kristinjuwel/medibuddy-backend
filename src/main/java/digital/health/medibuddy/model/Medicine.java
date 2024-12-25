package digital.health.medibuddy.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicine")
public class Medicine {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_id")
    private Long medId;
	
	@Column(name = "user_id")
    private Long userId;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "instructions")
    private String instructions;
    
    @Column(name = "dose")
    private String dose;
    
    @Column(name = "req_qty")
    private String requiredQty;
    
    @Column(name = "in_qty")
    private String initialQty;
    
    @Column(name = "cur_qty")
    private String currentQty;
    
    @Column(name = "unit")
    private String unit;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @Column(name = "attachments")
    private String attachments;

    @Column()
    private String fileType;  

    @Column()
    @ElementCollection
    private List <byte[]> files;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public Medicine() {
    	
    }

	public Medicine(Long medId, Long userId, User user, String name, String description, String instructions,
			String dose, String requiredQty, String initialQty, String currentQty, String unit, LocalDateTime createdAt,
			LocalDateTime updatedAt, String attachments, String fileType, List<byte[]> files) {
		super();
		this.medId = medId;
		this.userId = userId;
		this.user = user;
		this.name = name;
		this.description = description;
		this.instructions = instructions;
		this.dose = dose;
		this.requiredQty = requiredQty;
		this.initialQty = initialQty;
		this.currentQty = currentQty;
		this.unit = unit;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.attachments = attachments;
		this.fileType = fileType;
		this.files = files;
	}

	public Long getMedId() {
		return medId;
	}

	public void setMedId(Long medId) {
		this.medId = medId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getRequiredQty() {
		return requiredQty;
	}

	public void setRequiredQty(String requiredQty) {
		this.requiredQty = requiredQty;
	}

	public String getInitialQty() {
		return initialQty;
	}

	public void setInitialQty(String initialQty) {
		this.initialQty = initialQty;
	}

	public String getCurrentQty() {
		return currentQty;
	}

	public void setCurrentQty(String currentQty) {
		this.currentQty = currentQty;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public List<byte[]> getFiles() {
		return files;
	}

	public void setFiles(List<byte[]> files) {
		this.files = files;
	}

	
}
