package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.dto.UserFollowedDTO;

public interface IUserService {
 UserFollowedDTO followUser(int userId, int userIdToFollow);

    UserFollowedDTO unfollowUser(int userId, int userIdToUnfollow);
    
}
