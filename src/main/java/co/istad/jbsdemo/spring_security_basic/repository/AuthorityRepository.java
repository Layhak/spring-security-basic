package co.istad.jbsdemo.spring_security_basic.repository;

import co.istad.jbsdemo.spring_security_basic.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Optional<Authority> findByName(String name);
}
