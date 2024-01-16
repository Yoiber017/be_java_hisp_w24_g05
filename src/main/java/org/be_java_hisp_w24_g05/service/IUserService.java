package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.entity.Post;

import java.util.List;


import org.be_java_hisp_w24_g05.dto.PostDto;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.dto.UserFollowersDto;
import org.be_java_hisp_w24_g05.dto.UserFollowedDTO;
import java.util.List;

public interface IUserService {

     List<Post> recentPostsOfFollowedUsers(int userId, String order);

 User makePost(PostDto post);
 UserFollowedDTO followUser(int userId, int userIdToFollow);

    List<UserFollowersDto> searchUserFollowers(Integer userId, String order);

    UserFollowedDTO unfollowUser(int userId, int userIdToUnfollow);

}
