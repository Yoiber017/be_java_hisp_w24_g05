package org.be_java_hisp_w24_g05.service;

import org.be_java_hisp_w24_g05.dto.CountFollowersDTO;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.NotFoundException;
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
    public CountFollowersDTO searchUserFollowers(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return userToCountFollowersDTO(user);
    }

    @Override
    public UserFollowedDTO followUser(int userId, int userIdToFollow) {
        User user = this.userRepository.addFollower(userId, userIdToFollow);
        return userToUserFollowedDTO(user);
    }

    @Override
    public UserFollowedDTO unfollowUser(int userId, int userIdToUnfollow) {
        User user = this.userRepository.removeFollower(userId, userIdToUnfollow);
        return userToUserFollowedDTO(user);
    }

    private UserFollowedDTO userToUserFollowedDTO(User user) {
        return new UserFollowedDTO(
                user.getUserId(),
                user.getUserName(),
                user.getFollowed().size()
        );
    }

    private CountFollowersDTO userToCountFollowersDTO(User user) {
        return new CountFollowersDTO(
                user.getUserId(),
                user.getUserName(),
                user.getFollowers().size()
        );
    }

}
