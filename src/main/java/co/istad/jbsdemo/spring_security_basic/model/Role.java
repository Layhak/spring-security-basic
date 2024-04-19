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
@Table(name = "role_tbl")
public class Role {

    @ManyToMany
    Set<Authority> authorities;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;
    private String name;
    private String description;
}
