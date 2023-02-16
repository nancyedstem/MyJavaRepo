package com.edstem.demo.service;

import com.edstem.demo.config.MapperConfig;
import com.edstem.demo.dto.CreateGraveSiteRequest;
import com.edstem.demo.model.GraveSiteDetails;
import com.edstem.demo.repository.GraveDetailsRepository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class GraveSiteDetailsService {
    private final MapperConfig mapperConfig;

    private final GraveDetailsRepository graveDetailsRepository;

    @Autowired
    public GraveSiteDetailsService(
            MapperConfig mapperConfig, GraveDetailsRepository graveDetailsRepository) {
        this.mapperConfig = mapperConfig;
        this.graveDetailsRepository = graveDetailsRepository;
    }

    @SneakyThrows
    @Transactional
    public GraveSiteDetails createGraveSite(CreateGraveSiteRequest request) {
        GraveSiteDetails graveSiteDetails =
                mapperConfig.setModelMapper().map(request, GraveSiteDetails.class);

        graveSiteDetails = graveDetailsRepository.save(graveSiteDetails);
        log.info("GraveSite created successfully with id: {}", graveSiteDetails.getGraveId());
        return graveSiteDetails;
    }
}
