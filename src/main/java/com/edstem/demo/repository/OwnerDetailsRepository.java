package com.edstem.demo.repository;

import com.edstem.demo.model.OwnerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerDetailsRepository extends JpaRepository<OwnerDetails,Long> {
}
