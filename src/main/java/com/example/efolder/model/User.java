package com.example.efolder.model;

import lombok.*;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Polymorphism(type = PolymorphismType.EXPLICIT)
//@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    protected Long id;

    @Column(nullable = false)
    protected String username;

    @Setter
    @Column(nullable = false)
    protected String password;

    @ManyToMany(fetch = FetchType.LAZY)
    protected Collection<Role> roles = new ArrayList<>();

    public void addRole(Role role){
        for(Role r: this.roles){
            if(r.getRoleName().equals(role.getRoleName()))
               return;
        }
        this.roles.add(role);
    }


}
