package com.rudy.ladl.service;

import com.rudy.ladl.controller.UserController;
import com.rudy.ladl.entity.user.Role;
import com.rudy.ladl.entity.user.User;
import com.rudy.ladl.exception.EmailNotAvailableException;
import com.rudy.ladl.exception.UsernameNotAvailableException;
import com.rudy.ladl.repository.RoleRepository;
import com.rudy.ladl.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

;

@Transactional
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Add a new user in the database
     * @param user The user to add
     * @return return the new user with his Id
     * @throws EmailNotAvailableException Exception thrown if the email is already used by another user
     * @throws UsernameNotAvailableException Exception thrown if the username is already used by another user
     */
    public User addUser(User user) throws EmailNotAvailableException, UsernameNotAvailableException {
        if (userRepository.existsByEmail(user.getEmail())) throw new EmailNotAvailableException();
        if (userRepository.existsByUsername(user.getUsername())) throw new UsernameNotAvailableException();
        Role role = roleRepository.getByName("ROLE_USER");
        user.addRole(role);
        user.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }


    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
