package org.be_java_hisp_w24_g05.service;

import org.be_java_hisp_w24_g05.dto.PostDto;
import org.be_java_hisp_w24_g05.dto.ProductDto;
import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.entity.Product;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.dto.UserFollowedDTO;
import org.be_java_hisp_w24_g05.entity.User;
import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Override
    public User makePost(PostDto p) {
        ProductDto pDto = p.product();
        Product product = new Product(pDto.productId(), pDto.productName(), pDto.type(), pDto.brand(), pDto.color(), pDto.note());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(p.date(), formatter);
        Post post = new Post(0, p.userId(), date, product, p.category(), p.price());
        return userRepository.addPost(post);
    }

    @Override
    public UserFollowedDTO followUser(int userId, int userIdToFollow) {
        User user = this.userRepository.addFollower(userId, userIdToFollow);

        return new UserFollowedDTO(
                user.getUserId(),
                user.getUserName(),
                user.getFollowed().size()
        );
    }

    @Override
    public UserFollowedDTO unfollowUser(int userId, int userIdToUnfollow) {

        User user = this.userRepository.removeFollower(userId, userIdToUnfollow);

        return new UserFollowedDTO(
                user.getUserId(),
                user.getUserName(),
                user.getFollowed().size()
        );
    }
}
