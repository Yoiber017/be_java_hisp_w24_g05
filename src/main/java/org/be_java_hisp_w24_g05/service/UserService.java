package org.be_java_hisp_w24_g05.service;

import org.be_java_hisp_w24_g05.dto.CountFollowersDTO;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.NotFoundException;
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
        return new CountFollowersDTO(userId, user.getUserName(), user.getFollowers().size());
    }

}
