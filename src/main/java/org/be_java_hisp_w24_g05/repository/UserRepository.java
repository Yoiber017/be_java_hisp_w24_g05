package org.be_java_hisp_w24_g05.repository;

import org.be_java_hisp_w24_g05.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository{

    private ArrayList<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<User> findAll() {
        return null;
    }
}
