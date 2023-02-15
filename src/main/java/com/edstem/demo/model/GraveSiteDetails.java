package com.edstem.demo.model;

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
@Table(name="grave_details")
public class GraveSiteDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="grave_id")
    private long grave_id;
}
