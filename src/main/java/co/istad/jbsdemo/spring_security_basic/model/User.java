package co.istad.jbsdemo.spring_security_basic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users_tbl")
public class User {
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String email;
    private String password;
}
