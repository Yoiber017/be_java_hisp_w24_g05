package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.dto.*;
import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.entity.Product;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.NotFoundException;
import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;


    public List<Post> recentPostsOfFollowedUsers(int userId, String order){
        return userRepository.recentPostsOfFollowedUsers(userId, order);
    }
    @Override
    public User makePost(PostDto p) {
        ProductDto pDto = p.product();
        Product product = new Product(pDto.productId(), pDto.productName(), pDto.type(), pDto.brand(), pDto.color(), pDto.note());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(p.date(), formatter);
        Post post = new Post(0, p.userId(), date, product, p.category(), p.price());
        return userRepository.addPost(post);
    }

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

    public List<UserFollowedByDto> getSellerFollowedByUser(Integer userId, String order) {
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

    private UserFollowedByDto convertUserFollowedToDto(User u,List<UserDto> users){
        return new UserFollowedByDto(
                u.getUserId(),
                u.getUserName(),users
        );
    }


}
