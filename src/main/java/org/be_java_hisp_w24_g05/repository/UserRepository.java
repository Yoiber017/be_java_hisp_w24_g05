package org.be_java_hisp_w24_g05.repository;

import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.exception.BadRequestException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

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

    @Override
    public User addPost(Post post) {

        User user = this.users.stream().filter(u -> u.getUserId() == post.getUserId()).findFirst().orElse(null);
        if(Objects.isNull(user) ) throw new BadRequestException("User does not exist");
        List<Post> posts = user.getPosts();
        Integer postId = users.size();
        post.setPostId(postId);
        posts.add(post);
        user.setPosts(posts);
        return user;
    }
}
