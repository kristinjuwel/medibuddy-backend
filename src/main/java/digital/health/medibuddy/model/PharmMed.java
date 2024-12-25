package digital.health.medibuddy.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Table(name = "pmed")
public class PharmMed {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pmed_id")
    private Long pmedId;
	
	@Column(name = "name")
    private String name;

	@Column(name = "details")
    private String details;
	
	@Column(name = "qty")
    private Long qty;
	
	@Column(name = "avail")
    private Boolean avail;
	
	@Column(name = "pharm_id")
    private Long pharmId;

    @ManyToOne()
    @JoinColumn(name = "pharm_id", referencedColumnName = "pharm_id", insertable = false, updatable = false)
    private Pharmacy pharmacy;


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
    
    public PharmMed() {
    	
    }

	public PharmMed(Long pmedId, String name, String details, Long qty, Boolean avail, Long pharmId,
			Pharmacy pharmacy, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.pmedId = pmedId;
		this.name = name;
		this.details = details;
		this.qty = qty;
		this.avail = avail;
		this.pharmId = pharmId;
		this.pharmacy = pharmacy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getPmedId() {
		return pmedId;
	}

	public void setPmedId(Long pmedId) {
		this.pmedId = pmedId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public Boolean getAvail() {
		return avail;
	}

	public void setAvail(Boolean avail) {
		this.avail = avail;
	}

	public Long getPharmId() {
		return pharmId;
	}

	public void setPharmId(Long pharmId) {
		this.pharmId = pharmId;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
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
