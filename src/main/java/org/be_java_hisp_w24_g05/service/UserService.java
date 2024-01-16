package org.be_java_hisp_w24_g05.service;

import org.be_java_hisp_w24_g05.dto.UserFollowedDTO;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserFollowedDTO followUser(int userId, int userIdToFollow) {
        User user = this.userRepository.addFollower(userId, userIdToFollow);

        return new UserFollowedDTO(
                user.getUserId(),
                user.getUserName(),
                user.getFollowed().size()
        );
    }

    @Override
    public UserFollowedDTO unfollowUser(int userId, int userIdToUnfollow) {

        User user = this.userRepository.removeFollower(userId, userIdToUnfollow);

        return new UserFollowedDTO(
                user.getUserId(),
                user.getUserName(),
                user.getFollowed().size()
        );
    }
}
