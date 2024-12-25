package digital.health.medibuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import digital.health.medibuddy.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserId(Long userId);
    List<User> findAll();
    User findByEmail(String email);
    User findByUsername(String username);
}
