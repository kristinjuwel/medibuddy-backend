package digital.health.medibuddy.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
@Table(name = "schedule")
public class MedSched {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sched_id")
    private Long schedId;
	
	@Column(name = "med_id")
    private Long medId;

    @ManyToOne()
    @JoinColumn(name = "med_id", referencedColumnName = "med_id", insertable = false, updatable = false)
    private Medicine medicine;

    @Column(name = "day")
    private LocalDate day;
    
    @Column(name = "time")
    private LocalTime time;
    
    @Column(name = "time_taken")
    private LocalDateTime timeTaken;

    @Column(name = "taken")
    private Boolean taken;
    
    @Column(name = "qty_taken")
    private String qtyTaken;
    
    @Column(name = "action")
    private String action;

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
    
    public MedSched() {
    	
    }

	public MedSched(Long schedId, Long medId, Medicine medicine, LocalDate day, LocalTime time, LocalDateTime timeTaken, Boolean taken, String qtyTaken,
			String action, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.schedId = schedId;
		this.medId = medId;
		this.medicine = medicine;
		this.day = day;
		this.time = time;
		this.timeTaken = timeTaken;
		this.taken = taken;
		this.qtyTaken = qtyTaken;
		this.action = action;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getSchedId() {
		return schedId;
	}

	public void setSchedId(Long schedId) {
		this.schedId = schedId;
	}

	public Long getMedId() {
		return medId;
	}

	public void setMedId(Long medId) {
		this.medId = medId;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDateTime getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(LocalDateTime timeTaken) {
		this.timeTaken = timeTaken;
	}

	public Boolean getTaken() {
		return taken;
	}

	public void setTaken(Boolean taken) {
		this.taken = taken;
	}

	public String getQtyTaken() {
		return qtyTaken;
	}

	public void setQtyTaken(String qtyTaken) {
		this.qtyTaken = qtyTaken;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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
