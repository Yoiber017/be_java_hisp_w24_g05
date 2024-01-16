package org.be_java_hisp_w24_g05.service;



import org.be_java_hisp_w24_g05.dto.PostDto;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.dto.UserFollowersDto;
import org.be_java_hisp_w24_g05.dto.UserFollowedDTO;
import java.util.List;

public interface IUserService {

 User makePost(PostDto post);
 UserFollowedDTO followUser(int userId, int userIdToFollow);

    List<UserFollowersDto> searchUserFollowers(Integer userId, String order);
    UserFollowedDTO followUser(int userId, int userIdToFollow);
    UserFollowedDTO unfollowUser(int userId, int userIdToUnfollow);

}
