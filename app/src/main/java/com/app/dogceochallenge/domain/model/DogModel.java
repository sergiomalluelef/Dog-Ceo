package com.app.dogceochallenge.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DogModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("subName")
    private String subName;

    public DogModel() {
    }

    public DogModel(String name, String subName) {
        this.name = name;
        this.subName = subName;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("subNames")
    public String getSubName() {
        return subName;
    }
    @JsonProperty("subNames")
    public void setSubName(String subName) {
        this.subName = subName;
    }
}

