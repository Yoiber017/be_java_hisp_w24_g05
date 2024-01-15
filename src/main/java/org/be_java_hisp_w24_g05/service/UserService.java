package org.be_java_hisp_w24_g05.service;

import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;


    public List<Post> recentPostsOfFollowedUsers(int userId, String order){
        return userRepository.recentPostsOfFollowedUsers(userId, order);
    }
}
