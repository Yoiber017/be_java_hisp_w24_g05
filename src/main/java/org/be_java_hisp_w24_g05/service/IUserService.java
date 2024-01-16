package org.be_java_hisp_w24_g05.service;


import org.be_java_hisp_w24_g05.dto.PostDto;
import org.be_java_hisp_w24_g05.entity.User;

public interface IUserService {
    User makePost(PostDto post);
}
