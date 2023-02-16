package com.edstem.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.edstem.demo.config.MapperConfig;
import com.edstem.demo.dto.CreateOwnerRequest;
import com.edstem.demo.exception.OwnerServiceException;
import com.edstem.demo.model.GraveSiteDetails;
import com.edstem.demo.model.OwnerDetails;
import com.edstem.demo.repository.GraveDetailsRepository;
import com.edstem.demo.repository.OwnerDetailsRepository;
import java.util.Optional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OwnerDetailsServiceTest {
    @Spy MapperConfig mockConfigMapper;

    @Mock GraveDetailsRepository graveDetailsRepository;
    @Mock OwnerDetailsRepository ownerDetailsRepository;
    private OwnerDetailsService ownerDetailsService;

    @BeforeEach
    void init() {
        ownerDetailsService =
                new OwnerDetailsService(
                        mockConfigMapper, ownerDetailsRepository, graveDetailsRepository);
    }

    @SneakyThrows
    @Test
    @DisplayName("createEmployee success")
    void testCreateEmployee_Success() {
        // Given

        GraveSiteDetails graveSiteDetails = GraveSiteDetails.builder().graveId(100).build();
        // When

        CreateOwnerRequest request =
                CreateOwnerRequest.builder()
                        .city("Trivandrum")
                        .graveId(graveSiteDetails.getGraveId())
                        .emailId("satheesh@gmail.com")
                        .firstLineAddress("sathesh street")
                        .firstName("Satheesh")
                        .lastName("moham")
                        .memo("This is a graveyard in Trivandrum")
                        .secLineAddress("trivandrum")
                        .state("Kerala")
                        .zip(345678)
                        .build();
        OwnerDetails details =
                OwnerDetails.builder()
                        .id(100L)
                        .city(request.getCity())
                        .emailId(request.getEmailId())
                        .firstLineAddress(request.getFirstLineAddress())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .memo(request.getMemo())
                        .secLineAddress(request.getSecLineAddress())
                        .state(request.getState())
                        .zip(request.getZip())
                        .build();
        when(graveDetailsRepository.findById(any())).thenReturn(Optional.of(graveSiteDetails));
        when(ownerDetailsRepository.save(any())).thenReturn(details);

        ownerDetailsService.createOwner(request);
        OwnerDetails expected =
                OwnerDetails.builder()
                        .id(1L)
                        .graveSiteDetails(
                                GraveSiteDetails.builder().graveId(request.getGraveId()).build())
                        .city(request.getCity())
                        .graveSiteDetails(graveSiteDetails)
                        .emailId(request.getEmailId())
                        .firstLineAddress(request.getFirstLineAddress())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .memo(request.getMemo())
                        .secLineAddress(request.getSecLineAddress())
                        .state(request.getState())
                        .zip(request.getZip())
                        .build();
        ArgumentCaptor<OwnerDetails> ownerDetailsArgumentCaptor =
                ArgumentCaptor.forClass(OwnerDetails.class);
        verify(ownerDetailsRepository).save(ownerDetailsArgumentCaptor.capture());
        assertThat(
                ownerDetailsArgumentCaptor.getValue(),
                allOf(
                        hasProperty("firstName", equalTo(request.getFirstName())),
                        hasProperty("lastName", equalTo(expected.getLastName())),
                        hasProperty("firstLineAddress", equalTo(expected.getFirstLineAddress())),
                        hasProperty("memo", equalTo(expected.getMemo())),
                        hasProperty("state", equalTo(expected.getState()))));
    }

    @Test
    @DisplayName("createOwner failure")
    void testCreateOwner_Failure() {
        // Given
        CreateOwnerRequest request =
                CreateOwnerRequest.builder()
                        .city("Trivandrum")
                        .graveId(null)
                        .emailId("satheesh@gmail.com")
                        .firstLineAddress("sathesh street")
                        .firstName("Satheesh")
                        .lastName("moham")
                        .memo("This is a graveyard in Trivandrum")
                        .secLineAddress("trivandrum")
                        .state("Kerala")
                        .zip(345678)
                        .build();

        assertThrows(
                OwnerServiceException.class,
                () -> {

                    // When
                    ownerDetailsService.createOwner(request);
                });
    }
}
