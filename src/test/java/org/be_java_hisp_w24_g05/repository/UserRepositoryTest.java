package org.be_java_hisp_w24_g05.repository;

import org.be_java_hisp_w24_g05.common.Data;
import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.entity.Product;
import org.be_java_hisp_w24_g05.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {

    private final IUserRepository userRepository = new UserRepository();

    private final Data data = new Data();

    @Test
    public void loadData() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<User> expected = data.loadData();

        Method method = userRepository.getClass().getDeclaredMethod("loadData");
        method.setAccessible(true);
        List<User> result = (List<User>) method.invoke(userRepository);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find user by id")
    public void findById() {
        User expected = data.loadData().get(0);

        Optional<User> result = userRepository.findById(1);

        Assertions.assertEquals(expected, result.get());
    }

    @Test
    @DisplayName("Find user by id not found")
    public void findByIdNotFound() {
        Optional<User> result = userRepository.findById(100);

        Assertions.assertEquals(Optional.empty(), result);
    }


}
