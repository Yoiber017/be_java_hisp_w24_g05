package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.dto.UserFollowedDTO;

import org.be_java_hisp_w24_g05.dto.CountFollowersDTO;

public interface IUserService {
    UserFollowedDTO followUser(int userId, int userIdToFollow);

    UserFollowedDTO unfollowUser(int userId, int userIdToUnfollow);

    CountFollowersDTO searchUserFollowers(Integer userId);
}
