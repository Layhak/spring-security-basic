package co.istad.jbsdemo.spring_security_basic.repository;

import co.istad.jbsdemo.spring_security_basic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
