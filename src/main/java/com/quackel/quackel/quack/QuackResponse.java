package com.quackel.quackel.quack;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuackResponse {

    @JsonProperty("id")
    private long quackID;

    @JsonProperty("body")
    private String body;

    @JsonProperty("userId")
    private long userId;
}
