package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.entity.Post;

import java.util.List;

public interface IUserService {

     List<Post> recentPostsOfFollowedUsers(int userId, String order);
}
