package com.edstem.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = CreateGraveSiteRequest.CreateGraveSiteRequestBuilder.class)
public class CreateGraveSiteRequest {
    @JsonPOJOBuilder(withPrefix = "")
    public static class CreateOwnerRequestBuilder {}
}
