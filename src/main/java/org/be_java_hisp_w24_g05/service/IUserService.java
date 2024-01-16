package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.dto.*;
import org.be_java_hisp_w24_g05.entity.Post;

import java.util.List;


import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.dto.UserFollowersDto;

import java.util.List;

public interface IUserService {

    public List<UserFollowersDto> searchUserFollowers(Integer userId, String order);

    List<UserFollowedByDto> getSellerFollowedByUser(Integer userId, String order);

    List<Post> recentPostsOfFollowedUsers(int userId, String order);

    User makePost(PostDto post);

    UserFollowedDTO followUser(int userId, int userIdToFollow);

    UserFollowedDTO unfollowUser(int userId, int userIdToUnfollow);
}
