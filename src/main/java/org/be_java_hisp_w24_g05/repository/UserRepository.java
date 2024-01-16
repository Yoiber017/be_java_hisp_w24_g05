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
        return users.stream().filter(user -> user.getUserId().equals(id)).findFirst();
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

    @Override
    public User removeFollower(int userId, int userIdToUnfollow) {

        User user = this.users.stream().filter(u -> u.getUserId() == userId).findFirst().orElse(null);

        if (user != null) {
            User userToUnfollow = user.getFollowed().stream().filter(u -> u.getUserId() == userIdToUnfollow).findFirst().orElse(null);
            if (userToUnfollow != null) {
                user.getFollowed().remove(userToUnfollow);
                userToUnfollow.getFollowers().remove(user);
                return user;
            }
        }
        throw new BadRequestException("User not found");
    }
}
