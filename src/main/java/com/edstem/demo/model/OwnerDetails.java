package com.edstem.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="owner_details")
public class OwnerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @JsonIgnore
    private long id;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="emailId")
    private String emailId;
    @Column(name="firstLineAddress")
    private String firstLineAddress;
    @Column(name="secLineAddress")
    private String secLineAddress;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="zip")
    private int zip;
    @Column(name="memo")
    private String memo;
    @JoinColumn(name = "grave_id", nullable = false)
    private long graveId;
}
