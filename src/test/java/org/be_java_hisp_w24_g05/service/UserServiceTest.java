package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.common.Data;
import org.be_java_hisp_w24_g05.dto.UserFollowedDto;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.BadRequestException;
import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final Data data = new Data();

    @Test
    @DisplayName("[US01] - Test of verify id user to follow exists")
    public void followUserTest() {

        UserFollowedDto expected = new UserFollowedDto(2, "User2", 1);

        User userExpected = new User(2, "User2", new ArrayList<>(), List.of(data.getUSER_3()), new ArrayList<>());

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(data.loadData().get(1)));
        when(userRepository.findById(2)).thenReturn(Optional.ofNullable(data.loadData().get(2)));
        when(userRepository.addFollower(data.loadData().get(1), data.loadData().get(2))).thenReturn(userExpected);


        UserFollowedDto result = userService.followUser(1, 2);

        assertEquals(expected, result);

    }

    @Test
    @DisplayName("[US01] - Test of verify id user to follow don't exists")
    public void followUserNoExist(){
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(data.loadData().get(1)));
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, () -> {
            userService.followUser(1, 2);
        });
    }

    @Test
    @DisplayName("[US07] - Test of verify user to unfollow exists")
    public void unfollowUserTest() {

        UserFollowedDto expected = new UserFollowedDto(1, "User1", 1);

        User userExpected = new User(1, "User1", new ArrayList<>(), List.of(data.getUSER_3()), new ArrayList<>());

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(data.loadData().get(0)));
        when(userRepository.removeFollower(data.loadData().get(0), 2)).thenReturn(userExpected);

        UserFollowedDto result = userService.unfollowUser(1, 2);

        assertEquals(expected, result);

    }

    @Test
    @DisplayName("[US07] -Test of verify user to unfollow don't exists")
    public void unfollowUserNoExist(){
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, () -> {
            userService.unfollowUser(1, 2);
        });
    }




}
