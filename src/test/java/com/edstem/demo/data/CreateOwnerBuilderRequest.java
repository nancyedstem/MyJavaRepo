package com.edstem.demo.data;

import com.edstem.demo.model.GraveSiteDetails;
import com.edstem.demo.model.OwnerDetails;

public class CreateOwnerBuilderRequest {
    GraveSiteDetails graveSiteDetails =GraveSiteDetails.builder().graveId(1L).build();
    OwnerDetails request = OwnerDetails.builder().city("Trivandrum")
            .graveSiteDetails(graveSiteDetails)
            .emailId("satheesh@gmail.com")
            .firstLineAddress("sathesh street")
            .firstName("Satheesh")
            .lastName("moham")
            .memo("This is a graveyard in Trivandrum")
            .secLineAddress("trivandrum")
            .state("Kerala")
            .zip(345678).build();

}
