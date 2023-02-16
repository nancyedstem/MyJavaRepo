package com.edstem.demo.controller;

import com.edstem.demo.dto.CreateGraveSiteRequest;
import com.edstem.demo.dto.Response;
import com.edstem.demo.model.GraveSiteDetails;
import com.edstem.demo.service.GraveSiteDetailsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/api/grave", produces = "application/json")
public class GraveDetailsController {
    @Autowired private GraveSiteDetailsService graveDetailsService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveGraveDetails(
            @Valid @RequestBody CreateGraveSiteRequest graveDetails) {

        GraveSiteDetails graveSiteDetails = graveDetailsService.createGraveSite(graveDetails);
        return new ResponseEntity<>(
                Response.builder()
                        .success(true)
                        .message(String.valueOf(graveSiteDetails.getGraveId()))
                        .build(),
                HttpStatus.OK);
    }
}
