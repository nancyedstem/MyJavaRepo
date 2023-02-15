package com.edstem.demo.controller;

import com.edstem.demo.dto.Response;
import com.edstem.demo.model.OwnerDetails;
import com.edstem.demo.repository.OwnerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/api/owner",produces = "application/json")
public class OwnerDetailsController {
    @Autowired
    private OwnerDetailsRepository ownerDetailsRepository;
    @PostMapping("/save")
    public ResponseEntity<Response> saveOwnerDetails(@Valid @RequestBody OwnerDetails ownerDetails){
        ownerDetailsRepository.save(ownerDetails);
        return new ResponseEntity<>(Response.builder().success(true).message(String.valueOf(ownerDetails.getId())).build(), HttpStatus.OK);
    }
}
