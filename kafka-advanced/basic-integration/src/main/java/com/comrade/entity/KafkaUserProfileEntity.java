package com.comrade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "KAFKA_USER_PROFILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
}
