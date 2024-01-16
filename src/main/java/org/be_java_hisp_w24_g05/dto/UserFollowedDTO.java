package org.be_java_hisp_w24_g05.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserFollowedDTO<UserDTO>(
        @JsonProperty("user_id")
        Integer userId,
        @JsonProperty("user_name")
        String userName,
        @JsonProperty("quantity_of_followers")
        int quantityOfFollowed
        ){};

