package org.be_java_hisp_w24_g05.dto;

import java.util.List;

public record UserFollowedDTO<UserDTO>(
        Integer userId,
        String userName,
        int quantityOfFollowed
        ){};

