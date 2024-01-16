package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.dto.UserFollowersDto;

import java.util.List;

public interface IUserService {

    public List<UserFollowersDto> searchUserFollowers(Integer userId, String order);
}
