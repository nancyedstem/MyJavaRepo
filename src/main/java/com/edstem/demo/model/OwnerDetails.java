package com.edstem.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Objects;

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
    private Long id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="emailId")
    @Email(message="Please provide a valid email address")
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
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
    @Pattern(regexp = "\\d{6}")
    private int zip;

    @Column(name="memo")
    private String memo;

    @OneToOne
    @JoinColumn(name = "grave_id",nullable = false, referencedColumnName = "grave_id")
    private GraveSiteDetails graveSiteDetails;

    public void linkGrave(GraveSiteDetails graveSiteDetails){
        if (Objects.nonNull(graveSiteDetails)) {
            this.graveSiteDetails = graveSiteDetails;
        }
    }
}
