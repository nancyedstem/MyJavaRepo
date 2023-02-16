package com.edstem.demo.service;

import com.edstem.demo.config.MapperConfig;
import com.edstem.demo.dto.CreateOwnerRequest;
import com.edstem.demo.exception.OwnerServiceException;
import com.edstem.demo.model.GraveSiteDetails;
import com.edstem.demo.model.OwnerDetails;
import com.edstem.demo.repository.GraveDetailsRepository;
import com.edstem.demo.repository.OwnerDetailsRepository;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class OwnerDetailsService {
    private final MapperConfig mapperConfig;

    private final OwnerDetailsRepository ownerDetailsRepository;
    private final GraveDetailsRepository graveDetailsRepository;

    @Autowired
    public OwnerDetailsService(
            MapperConfig mapperConfig,
            OwnerDetailsRepository ownerDetailsRepository,
            GraveDetailsRepository graveDetailsRepository) {
        this.mapperConfig = mapperConfig;
        this.ownerDetailsRepository = ownerDetailsRepository;
        this.graveDetailsRepository = graveDetailsRepository;
    }

    @SneakyThrows
    @Transactional
    public OwnerDetails createOwner(CreateOwnerRequest request) {
        Optional<GraveSiteDetails> graveSiteDetails =
                graveDetailsRepository.findById(request.getGraveId());

        if (graveSiteDetails.isEmpty()) {
            log.error("Grave id {} not found", request.getGraveId());

            throw new OwnerServiceException(
                    String.format("Grave id %s not found", request.getGraveId()));
        }
        OwnerDetails owner = mapperConfig.setModelMapper().map(request, OwnerDetails.class);
        owner.linkGrave(graveSiteDetails.get());
        owner = ownerDetailsRepository.save(owner);
        log.info("Owner created successfully with id: {}", owner.getId());
        return owner;
    }
}
