package org.be_java_hisp_w24_g05.service;

import org.be_java_hisp_w24_g05.dto.UserDto;
import org.be_java_hisp_w24_g05.dto.UserFollowersDto;
import org.be_java_hisp_w24_g05.dto.UserFollowedDto;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.NotFoundException;
import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserFollowersDto> searchUserFollowers(Integer userId, String order) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<User> users= userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")
        ).getFollowers();
        if(order.equals("name_asc")){
            users.sort(Comparator.comparing(User::getUserName));
        }else if(order.equals("name_desc")){
            users.sort(Comparator.comparing(User::getUserName).reversed());
        }
        return Collections.singletonList(convertUserFollowersToDto(user, users.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList())));

    }

    public List<UserFollowedDto> getSellerFollowedByUser(Integer userId, String order) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<User> followedList = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")
        ).getFollowed();
        if (followedList.isEmpty()) throw new NotFoundException("User ID: " + userId + " doesn't follow any seller.");
        if(order.equals("name_asc")){
            followedList.sort(Comparator.comparing(User::getUserName));
        }else if(order.equals("name_desc")){
            followedList.sort(Comparator.comparing(User::getUserName).reversed());
        }

        return Collections.singletonList(convertUserFollowedToDto(user, followedList.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList())));

    }
    private UserDto convertUserToDto(User u){
        return new UserDto(
                u.getUserId(),
                u.getUserName()
                );
    }
    private UserFollowersDto convertUserFollowersToDto(User u,List<UserDto> users){
        return new UserFollowersDto(
                u.getUserId(),
                u.getUserName(),users
        );
    }

    private UserFollowedDto convertUserFollowedToDto(User u,List<UserDto> users){
        return new UserFollowedDto(
                u.getUserId(),
                u.getUserName(),users
        );
    }


}
