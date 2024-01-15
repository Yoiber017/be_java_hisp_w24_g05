package org.be_java_hisp_w24_g05.controller;

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

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<CountFollowersDTO> searchUserFollowers(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.searchUserFollowers(userId));
    }
}
