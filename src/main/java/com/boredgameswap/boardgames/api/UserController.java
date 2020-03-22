package com.boredgameswap.boardgames.api;

import com.boredgameswap.boardgames.model.User;
import com.boredgameswap.boardgames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void create(@Valid @NotNull @RequestBody User user) {
        userService.create(user);
    }
//
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
//
//    @GetMapping(path = "{id}")
//    public User getUserById(@PathVariable("id") UUID id) {
//        return userService.getUserById(id)
//                .orElse(null);
//    }
//
//    @DeleteMapping(path = "{id}")
//    public void deleteUserById(@PathVariable("id") UUID id) {
//        userService.deleteUser(id);
//    }
//
//    @PutMapping(path = "{id}")
//    public void updateUser(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody User user) {
//        userService.updateUser(id, user);
//    }
}
