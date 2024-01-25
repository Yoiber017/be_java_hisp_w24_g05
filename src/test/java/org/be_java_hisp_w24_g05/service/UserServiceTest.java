package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.common.Data;
import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.entity.Product;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private Data data = new Data();
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void recentPostsOfFollowedUsersDateDescPositive(){
        //arrange

        Product product = new Product(4, "Laptop", "Electronics", "BrandX", "Silver", "Note1");
        Product product1 = new Product(1, "Laptop", "Electronics", "BrandX", "Silver", "Note1");
        Product product2 = new Product(2, "Phone", "Electronics", "BrandY", "Black", "Note2");
        Product product3 = new Product(3, "Camera", "Photography", "BrandZ", "Red", "Note3");

        //Lista de posts esperados de con fecha menor a 14 dias
        Post post1 = new Post(1, 1, LocalDate.now().minusDays(7), product1, 1, 100.0);
        Post post2 = new Post(2, 1, LocalDate.now().minusDays(14), product2, 2, 200.0);
        Post post3 = new Post(3, 2, LocalDate.now().minusDays(21), product3, 1, 150.0);
        Post post4 = new Post(4, 2, LocalDate.now().minusDays(3), product1, 2, 120.0);

        List<Post> expectedPosts = List.of(post2, post1, post4);

         //act

        

        //Mockito.when(userRepository.recentPostsOfFollowedUsers(1,"date_desc")).thenReturn(expectedPosts);

        var result = userRepository.recentPostsOfFollowedUsers(1,"date_desc");

        //assert

        Assertions.assertEquals(expectedPosts,result);
    }

    @Test
    public void recentPostsOfFollowedUsersDateAscPositive(){
        //arrange

        Product product = new Product(4, "Laptop", "Electronics", "BrandX", "Silver", "Note1");
        Product product1 = new Product(1, "Laptop", "Electronics", "BrandX", "Silver", "Note1");
        Product product2 = new Product(2, "Phone", "Electronics", "BrandY", "Black", "Note2");
        Product product3 = new Product(3, "Camera", "Photography", "BrandZ", "Red", "Note3");

        //Lista de posts esperados de con fecha menor a 14 dias
        Post post1 = new Post(1, 1, LocalDate.now().minusDays(7), product1, 1, 100.0);
        Post post2 = new Post(2, 1, LocalDate.now().minusDays(14), product2, 2, 200.0);
        Post post3 = new Post(3, 2, LocalDate.now().minusDays(21), product3, 1, 150.0);
        Post post4 = new Post(4, 2, LocalDate.now().minusDays(3), product1, 2, 120.0);

        List<Post> expectedPosts = List.of(post4, post1, post2);

        //act

        Mockito.when(userRepository.recentPostsOfFollowedUsers(1,"date_asc")).thenReturn(expectedPosts);

        var result = userService.recentPostsOfFollowedUsers(1,"date_asc");

        //quizas mejor comparo con lo que me da el repositorio?

        //assert

        Assertions.assertEquals(result, expectedPosts);
    }

}
