package org.be_java_hisp_w24_g05.repository;

import org.be_java_hisp_w24_g05.entity.Post;
import org.be_java_hisp_w24_g05.entity.User;

public interface IUserRepository extends ICrudRepository<User> {
    User addPost(Post post);
}
