package org.be_java_hisp_w24_g05.controller;

import org.be_java_hisp_w24_g05.service.IUserService;
import org.be_java_hisp_w24_g05.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> searchUserFollowers(@PathVariable Integer userId, @RequestParam(defaultValue = "") String order){

        return new ResponseEntity<>(userService.searchUserFollowers(userId,order), HttpStatus.OK);
    }
}
