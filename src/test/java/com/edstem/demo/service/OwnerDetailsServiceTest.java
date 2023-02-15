package com.edstem.demo.service;

import com.edstem.demo.config.MapperConfig;
import com.edstem.demo.dto.CreateOwnerRequest;
import com.edstem.demo.exception.OwnerServiceException;
import com.edstem.demo.model.GraveSiteDetails;
import com.edstem.demo.model.OwnerDetails;
import com.edstem.demo.repository.GraveDetailsRepository;
import com.edstem.demo.repository.OwnerDetailsRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerDetailsServiceTest {
    @Spy
    MapperConfig mockConfigMapper;

    @Mock
    GraveDetailsRepository graveDetailsRepository;
    @Mock
    OwnerDetailsRepository ownerDetailsRepository;
    private OwnerDetailsService ownerDetailsService;
private OwnerDetails owner;
    @BeforeEach
    void init() {
        ownerDetailsService =
                new OwnerDetailsService(
                        mockConfigMapper,ownerDetailsRepository,
                        graveDetailsRepository
                        );
    }

    @Test
    @DisplayName("createOwner failure")
    void testCreateOwner_Failure() {
        // Given
        CreateOwnerRequest request =
                CreateOwnerRequest.builder()
                        .city("Trivandrum").graveId(null)
                        .emailId("satheesh@gmail.com")
                        .firstLineAddress("sathesh street")
                        .firstName("Satheesh")
                        .lastName("moham")
                        .memo("This is a graveyard in Trivandrum")
                        .secLineAddress("trivandrum")
                        .state("Kerala")
                        .zip(345678).build();

        assertThrows(
                OwnerServiceException.class,
                () -> {

                    // When
                    ownerDetailsService.createOwner(request);
                });
    }

}
