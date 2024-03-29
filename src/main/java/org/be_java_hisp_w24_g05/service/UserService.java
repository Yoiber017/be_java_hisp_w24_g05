package org.be_java_hisp_w24_g05.service;

import org.be_java_hisp_w24_g05.common.ModelMapper;
import org.be_java_hisp_w24_g05.dto.CountFollowersDto;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.BadOrderException;
import org.be_java_hisp_w24_g05.exception.BadRequestException;
import org.be_java_hisp_w24_g05.exception.NotFoundException;

import org.be_java_hisp_w24_g05.dto.*;
import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public CountFollowersDto searchUserFollowers(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return modelMapper.userToCountFollowersDTO(user);
    }


    // Posts of followed users by user id from last 2 weeks sorted by date
    //in case none found throw not found exception
    public PostFollowedDto recentPostsOfFollowedUsers(int userId, String order){
        try {
            if (order.isEmpty()) order = "date_desc";
            if (!order.equalsIgnoreCase("date_asc") && !order.equalsIgnoreCase("date_desc")) {
                throw new BadRequestException("Order value not valid");
            }
            List<Post> posts = userRepository.recentPostsOfFollowedUsers(userId, order);
            return new PostFollowedDto(userId, posts);
        }
        catch (NoSuchElementException e) {
            throw new NotFoundException("User not found");
        }
        catch (BadRequestException e) {
            throw new BadRequestException("Order value not valid");
        }
    }


    @Override
    public UserFollowedDto followUser(int userId, int userIdToFollow) {
        if (userId == userIdToFollow)
            throw new BadRequestException("User with id " + userId + " cannot follow himself");

        User user = this.userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User with id " + userId + " not found"));
        User userToFollow = this.userRepository.findById(userIdToFollow).orElseThrow(() -> new BadRequestException("User with id " + userIdToFollow + " not found"));

        if (user.getFollowed().stream().filter(u -> u.getUserId() == userIdToFollow).findFirst().orElse(null) != null)
            throw new BadRequestException("User with id " + userIdToFollow + " already followed");

        User userTodDto = this.userRepository.addFollower(user, userToFollow);

        return modelMapper.userToUserFollowedDTO(userTodDto);
    }

    @Override
    public UserFollowedDto unfollowUser(int userId, int userIdToUnfollow) {
        if (userId == userIdToUnfollow)
            throw new BadRequestException("User with id " + userId + " cannot unfollow himself");

        User user = this.userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User with id " + userId + " not found"));

        if (user.getFollowed().stream().filter(u -> u.getUserId() == userIdToUnfollow).findFirst().orElse(null) == null)
            throw new BadRequestException("User with id " + userIdToUnfollow + " not followed");

        User userToDto = this.userRepository.removeFollower(user, userIdToUnfollow);

        return modelMapper.userToUserFollowedDTO(userToDto);
    }
    @Override
    public List<UserFollowersDto> searchUserFollowers(Integer userId, String order) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<User> users= new ArrayList<>(user.getFollowers());
        if (users.isEmpty()) throw new NotFoundException("User ID: " + userId + " doesn't have any followers.");
        if(order.equals("name_asc")  || order.equals("")){
            users.sort(Comparator.comparing(User::getUserName));
        }else if(order.equals("name_desc")){
            users.sort(Comparator.comparing(User::getUserName).reversed());
        }
        else{
            throw new BadOrderException("Order isn't valid, please use name_asc or name_desc.");
        }

        return Arrays.asList(modelMapper.convertUserFollowersToDto(user, users.stream()
                .map(modelMapper::convertUserToDto)
                .collect(Collectors.toList())));

    }

    public List<UserFollowedByDto> getSellerFollowedByUser(Integer userId, String order) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<User> followedList = user.getFollowed();
        if (followedList.isEmpty()) throw new NotFoundException("User ID: " + userId + " doesn't follow any seller.");
        if(order.equals("name_asc") || order.isEmpty()){
            followedList.sort(Comparator.comparing(User::getUserName));
        }else if(order.equals("name_desc")){
            followedList.sort(Comparator.comparing(User::getUserName).reversed());
        } else throw new BadRequestException(order + " parameter is not valid");

        return Collections.singletonList(modelMapper.convertUserFollowedToDto(user, followedList.stream()
                .map(modelMapper::convertUserToDto)
                .collect(Collectors.toList())));

    }


}
