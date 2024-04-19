package co.istad.jbsdemo.spring_security_basic.repository;

import co.istad.jbsdemo.spring_security_basic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(String name);
}
