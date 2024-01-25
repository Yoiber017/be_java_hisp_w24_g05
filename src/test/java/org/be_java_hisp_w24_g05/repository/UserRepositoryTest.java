package org.be_java_hisp_w24_g05.repository;

import org.be_java_hisp_w24_g05.common.Data;
import org.be_java_hisp_w24_g05.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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
}
