package com.urlshortener.urlShortener.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data //getter, setter, equals, toString, hashCode,
@NoArgsConstructor
@AllArgsConstructor
@Builder // Builder design patter
public class Url {

    @NotBlank
    @JsonProperty("url") // coming requests must include 'url' property
    @JsonAlias("result_url") // The response JSON from CLEAN URI includes result_url property for returned url.
    private String url;

}
