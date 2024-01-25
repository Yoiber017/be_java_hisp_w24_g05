package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.common.Data;
import org.be_java_hisp_w24_g05.dto.CountFollowersDto;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.NotFoundException;
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

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final Data data = new Data();

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
