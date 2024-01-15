package org.be_java_hisp_w24_g05.repository;

import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.BadRequestException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository{

    private ArrayList<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
        this.users.add(new User(1, "User 1", new ArrayList<>(), new ArrayList<>()));
        this.users.add(new User(2, "User 2", new ArrayList<>(), new ArrayList<>()));
        this.users.add(new User(3, "User 3", new ArrayList<>(), new ArrayList<>()));
        this.users.add(new User(4, "User 4", new ArrayList<>(), new ArrayList<>()));
        this.users.add(new User(5, "User 5", new ArrayList<>(), new ArrayList<>()));
        this.users.add(new User(6, "User 6", new ArrayList<>(), new ArrayList<>()));
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

    @Override
    public User addFollower(int userId, int userIdToFollow) {

        User user = this.users.stream().filter(u -> u.getUserId() == userId).findFirst().orElse(null);
        User userToFollow = this.users.stream().filter(u -> u.getUserId() == userIdToFollow).findFirst().orElse(null);

        if (user != null && userToFollow != null) {
            user.getFollowed().add(userToFollow);
            userToFollow.getFollowers().add(user);
            return user;
        }

        throw new BadRequestException("User not found");
    }
}
