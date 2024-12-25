package digital.health.medibuddy.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "pharmacy")
public class Pharmacy {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pharm_id")
    private Long pharmId;
	
	@Column(name = "name")
    private String name;

	@Column(name = "address")
    private String address;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public Pharmacy() {
    	
    }

	public Pharmacy(Long pharmId, String name, String address, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.pharmId = pharmId;
		this.name = name;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getPharmId() {
		return pharmId;
	}

	public void setPharmId(Long pharmId) {
		this.pharmId = pharmId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
    
}
