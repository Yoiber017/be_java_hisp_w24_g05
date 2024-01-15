package org.be_java_hisp_w24_g05.service;

import org.be_java_hisp_w24_g05.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
}
