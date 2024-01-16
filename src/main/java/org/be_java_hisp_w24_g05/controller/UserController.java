package org.be_java_hisp_w24_g05.controller;

import org.be_java_hisp_w24_g05.dto.UserFollowedDTO;
import org.be_java_hisp_w24_g05.dto.CountFollowersDTO;
import org.be_java_hisp_w24_g05.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<UserFollowedDTO> followUser(@PathVariable int userId, @PathVariable int userIdToFollow){

        UserFollowedDTO user = this.userService.followUser(userId, userIdToFollow);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<UserFollowedDTO> unfollowUser(@PathVariable int userId, @PathVariable int userIdToUnfollow){

        UserFollowedDTO user = this.userService.unfollowUser(userId, userIdToUnfollow);

        return ResponseEntity.ok(user);
    }


    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<CountFollowersDTO> searchUserFollowers(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.searchUserFollowers(userId));
    }
}
