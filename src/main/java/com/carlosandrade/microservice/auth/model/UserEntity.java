package com.carlosandrade.microservice.auth.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String encryptedPassword;


}
