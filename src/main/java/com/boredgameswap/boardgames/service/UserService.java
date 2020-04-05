package com.boredgameswap.boardgames.service;

import com.boredgameswap.boardgames.model.User;
import com.boredgameswap.boardgames.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User create(Map<String, Object> userInput) {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(userInput, User.class);
        String newPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public void deleteUser(UUID id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    public User getUserByEmail(String email) {
        List<User> users = Lists.newArrayList(userRepository.findAll());
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
//
//    public int updateUser(UUID id, User user) {
//        return userDao.updateUserById(id, user);
//    }
}

