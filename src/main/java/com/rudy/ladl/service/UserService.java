package com.rudy.ladl.service;

import com.rudy.ladl.core.user.User;
import com.rudy.ladl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User add(User pUser) {
        return null;
    }

    public void delete(Long pId) {

    }

    public void update(User pUser) {

    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
