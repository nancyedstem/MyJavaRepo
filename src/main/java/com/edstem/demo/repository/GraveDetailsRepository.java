package com.edstem.demo.repository;

import com.edstem.demo.model.GraveSiteDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraveDetailsRepository extends JpaRepository<GraveSiteDetails,Long> {
}
