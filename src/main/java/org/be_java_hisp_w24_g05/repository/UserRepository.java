package org.be_java_hisp_w24_g05.repository;

import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.entity.Product;
import org.be_java_hisp_w24_g05.entity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // Posts of followed users by user id from last 2 weeks sorted by date
    public List<Post> recentPostsOfFollowedUsers(int userId, String order) {

        List<Post> lisPosts = users.stream().filter(user -> user.getUserId() == userId)
                .findFirst().get().getFollowed().stream()
                .flatMap(user -> user.getPosts().stream())
                .filter(post -> post.getDate().isAfter(LocalDate.now().minusDays(14)))
                .sorted(Comparator.comparing(Post::getDate))
                .toList();

        if (order.equals("date_desc")){
            return lisPosts.stream().sorted(Comparator.comparing(Post::getDate).reversed()).toList();
        }
        else {
            return lisPosts;
        }

    }
}
