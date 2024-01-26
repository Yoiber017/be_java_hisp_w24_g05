package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.common.Data;
import org.be_java_hisp_w24_g05.dto.CountFollowersDto;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.NotFoundException;
import org.be_java_hisp_w24_g05.common.Data;
import org.be_java_hisp_w24_g05.dto.UserDto;
import org.be_java_hisp_w24_g05.dto.UserFollowedByDto;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.BadRequestException;

import org.be_java_hisp_w24_g05.dto.UserFollowersDto;

import org.be_java_hisp_w24_g05.exception.BadOrderException;

import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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


    @Test
    @DisplayName("T-0003 Verify an exception thrown when order is different to name_asc or name_des")
    public void searchUserFollowersExceptionTest(){

        // Arrange
        int userId = 1;
        String order = "name_des";
        User user = data.loadData().get(0);

        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Assert
        Assertions.assertThrows(
                BadOrderException.class,
                ()->{
                    userService.searchUserFollowers(userId, order);
                }
        );

    }

    @Test
    @DisplayName("T-0003 T-0004 Verify name_asc works as expected in searchUserFollowers")
    public void searchUserFollowersOrderByNameAscTest(){
        // Arrange
        int userId = 1;
        String order = "name_asc";
        User user = data.loadData().get(0);

        List<UserFollowersDto> expected = Collections.singletonList(
                                                        new UserFollowersDto(1,
                                                                            "User1",
                                                                            List.of(
                                                                                new UserDto(2, "User2"),
                                                                                new UserDto(3, "User3"),
                                                                                new UserDto(4, "User4"))));
        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Assert
        Assertions.assertEquals(expected, userService.searchUserFollowers(userId, order),"The order by name_asc is not working as expected");

    }
    @Test
    @DisplayName("T-0003 T-0004 Verify name_desc works as expected in searchUserFollowers")
    public void searchUserFollowersOrderByNameDescTest(){

        // Arrange
        int userId = 1;
        String order = "name_desc";
        User user = data.loadData().get(0);

        List<UserFollowersDto> expected = Collections.singletonList(
                new UserFollowersDto(1,
                        "User1",
                        List.of(
                                new UserDto(4, "User4"),
                                new UserDto(3, "User3"),
                                new UserDto(2, "User2"))));
        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Assert
        Assertions.assertEquals(expected, userService.searchUserFollowers(userId, order),"The order by name_desc is not working as expected");

    }


    @Test
    @DisplayName("T-0003 T-0004 Verify searchUserFollowers works as expected")
    public void searchUserFollowersTest(){

        // Arrange
        int userId = 1;
        String order = "";
        User user = data.loadData().get(0);

        List<UserFollowersDto> expected = Collections.singletonList(
                new UserFollowersDto(1,
                        "User1",
                        List.of(
                                new UserDto(2, "User2"),
                                new UserDto(3, "User3"),
                                new UserDto(4, "User4"))));
        // Act
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Assert
        Assertions.assertEquals(expected, userService.searchUserFollowers(userId, order),"The method searchUserFollowers is not working as expected");

    }
    @Test
    @DisplayName("[T-0007] - Verificar que la cantidad de seguidores de un determinado usuario sea correcta. (US-0002) - Usuario con id 1")
    public void searchUserFollowersId1() {

        CountFollowersDto expected = new CountFollowersDto(1, "User1", 2);

        User user = data.loadData().get(0);

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        CountFollowersDto result = userService.searchUserFollowers(1);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("[T-0007] - Verificar que la cantidad de seguidores de un determinado usuario sea correcta. (US-0002) - Usuario con id 2")
    public void searchUserFollowersId2() {

        CountFollowersDto expected = new CountFollowersDto(2, "User2", 0);

        User user = data.loadData().get(1);

        Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user));

        CountFollowersDto result = userService.searchUserFollowers(2);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("[T-0007] - Verificar que la cantidad de seguidores de un determinado usuario sea correcta. (US-0002) - Usuario con id 3")
    public void searchUserFollowersId3() {

        CountFollowersDto expected = new CountFollowersDto(3, "User3", 0);

        User user = data.loadData().get(2);

        Mockito.when(userRepository.findById(3)).thenReturn(Optional.of(user));

        CountFollowersDto result = userService.searchUserFollowers(3);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("[T-0007] - Verificar que la cantidad de seguidores de un determinado usuario sea correcta. (US-0002) - Usuario con id 4")
    public void searchUserFollowersId4() {

        CountFollowersDto expected = new CountFollowersDto(4, "User4", 0);

        User user = data.loadData().get(3);

        Mockito.when(userRepository.findById(4)).thenReturn(Optional.of(user));

        CountFollowersDto result = userService.searchUserFollowers(4);

        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("[T-0007] - Verificar que la cantidad de seguidores de un determinado usuario sea correcta. (US-0002) - Usuario no registrado")
    public void searchUserFollowersNotFound() {

        Mockito.when(userRepository.findById(10)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> userService.searchUserFollowers(10));
    }

}
