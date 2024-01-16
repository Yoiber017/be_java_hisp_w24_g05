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
        User usuario1 = new User(1, "aser 1", new ArrayList<>(), new ArrayList<>(),new  ArrayList<>());
        User usuario6 =new User(6, "bser 6", new ArrayList<>(), new ArrayList<>(),new  ArrayList<>());
        User usuario2 =new User(2, "dser 2", new ArrayList<>(), new ArrayList<>(),new  ArrayList<>());
        User usuario3 =new User(3, "cser 3", new ArrayList<>(), new ArrayList<>(),new  ArrayList<>());
        User usuario4 =new User(4, "aser 4", new ArrayList<>(), new ArrayList<>(),new  ArrayList<>());
        User usuario5 =new User(5, "fser 5", new ArrayList<>(), new ArrayList<>(),new  ArrayList<>());
        User usuario7 =new User(7, "gser 7", new ArrayList<>(), new ArrayList<>(),new  ArrayList<>());
        User usuario8 =new User(8, "hser 8", new ArrayList<>(), new ArrayList<>(),new  ArrayList<>());
        usuario1.getFollowers().add(usuario2);
        usuario1.getFollowers().add(usuario3);
        usuario1.getFollowers().add(usuario4);
        usuario2.getFollowers().add(usuario5);
        usuario2.getFollowers().add(usuario6);
        usuario3.getFollowers().add(usuario7);
        usuario3.getFollowers().add(usuario8);
        this.users.add(usuario1);
        this.users.add(usuario2);
        this.users.add(usuario3);
        this.users.add(usuario4);
        this.users.add(usuario5);
        this.users.add(usuario6);
        this.users.add(usuario7);
        this.users.add(usuario8);


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
