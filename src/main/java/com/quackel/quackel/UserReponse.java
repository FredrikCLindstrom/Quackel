package com.quackel.quackel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserReponse {

    @JsonProperty("id")
    private long Id;

    @JsonProperty("name")
    private String name;
}
