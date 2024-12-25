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
@Table(name = "carer")
public class Carer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carer_id")
    private Long carerId;
	
	@Column(name = "user_id")
    private Long userId;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(name = "relationship")
    private String relationship;
    
    @Column(name = "notify")
    private Boolean notify;

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
    
    public Carer() {
    	
    }

	public Carer(Long carerId, Long userId, User user, String email, String firstName, String middleName,
			String lastName, String relationship, Boolean notify, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.carerId = carerId;
		this.userId = userId;
		this.user = user;
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.relationship = relationship;
		this.notify = notify;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getCarerId() {
		return carerId;
	}

	public void setCarerId(Long carerId) {
		this.carerId = carerId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Boolean getNotify() {
		return notify;
	}

	public void setNotify(Boolean notify) {
		this.notify = notify;
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
