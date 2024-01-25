package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.common.Data;
import org.be_java_hisp_w24_g05.dto.UserDto;
import org.be_java_hisp_w24_g05.dto.UserFollowedByDto;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.BadRequestException;
import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final Data data = new Data();
    @Test
    @DisplayName("Verify correct order")
    public void getSellerFollowedByUserTest(){

        // Arrange
        int userId = 1;
        String order = "";
        User user = data.loadData().get(0);
        List<UserFollowedByDto> expected = Collections.singletonList(new UserFollowedByDto(1, "User1",
                List.of(new UserDto(
                        2, "User2"
                ), new UserDto(
                        3, "User3"
                ))));

        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        List<UserFollowedByDto> result = userService.getSellerFollowedByUser(userId, order);

        // Assert
        Assertions.assertEquals(expected, result, "The alphabetic order doesn't exists");

    }
    @Test
    @DisplayName("Verify correct order asc")
    public void getSellerFollowedByUserNameAscTest(){

        // Arrange
        int userId = 1;
        String order = "name_asc";
        User user = data.loadData().get(0);
        List<UserFollowedByDto> expected = Collections.singletonList(new UserFollowedByDto(1, "User1",
                List.of(new UserDto(
                        2, "User2"
                ), new UserDto(
                        3, "User3"
                ))));

        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        List<UserFollowedByDto> result = userService.getSellerFollowedByUser(userId, order);

        // Assert
        Assertions.assertEquals(expected, result, "The alphabetic order doesn't exists");

    }

    @Test
    @DisplayName("Verify correct order desc")
    public void getSellerFollowedByUserNameDescTest(){

        // Arrange
        int userId = 1;
        String order = "name_desc";
        User user = data.loadData().get(0);
        List<UserFollowedByDto> expected = Collections.singletonList(new UserFollowedByDto(1, "User1",
                List.of(new UserDto(
                        3, "User3"
                ), new UserDto(
                        2, "User2"
                ))));

        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        List<UserFollowedByDto> result = userService.getSellerFollowedByUser(userId, order);

        // Assert
        Assertions.assertEquals(expected, result, "The alphabetic order doesn't exists");

    }


    @Test
    @DisplayName("Verify name_asc and name_desc exist")
    public void getSellerFollowedByUserOrderTest(){

        // Arrange
        int userId = 1;
        String order = "name_des";
        User user = data.loadData().get(0);

        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Assert
        Assertions.assertThrows(
                BadRequestException.class,
                ()->{
                    userService.getSellerFollowedByUser(userId, order);
                }
        ).printStackTrace();

    }


}
