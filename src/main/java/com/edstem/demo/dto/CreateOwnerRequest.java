package com.edstem.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = CreateOwnerRequest.CreateOwnerRequestBuilder.class)
public class CreateOwnerRequest {
    @JsonPOJOBuilder(withPrefix = "")
    public static class CreateOwnerRequestBuilder {}

    @ApiModelProperty(notes = "First name of owner is mandatory", required = true)
    @NotEmpty(message = "First name is mandatory")
    private final String firstName;

    @ApiModelProperty(notes = "Last name of owner is mandatory")
    private final String lastName;

    @NotEmpty
    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    @ApiModelProperty(notes = "Email of owner is mandatory")
    private String emailId;

    @NotEmpty
    @ApiModelProperty(notes = "firstLineAddress of owner is mandatory")
    private String firstLineAddress;

    @NotEmpty
    @ApiModelProperty(notes = "Email of owner is mandatory")
    private String secLineAddress;

    @NotEmpty
    @ApiModelProperty(notes = "City of owner is mandatory")
    private String city;

    @NotEmpty
    @ApiModelProperty(notes = "State of owner is mandatory")
    private String state;

    @NotNull
    @Pattern(regexp = "\\d{6}")
    @ApiModelProperty(notes = "zip of owner is mandatory")
    private int zip;

    @ApiModelProperty(notes = "Memo of owner")
    private String memo;

    @NotNull
    @ApiModelProperty(notes = "grave id")
    private Long graveId;
}
