/*
Repository
Entities
Exceptions
Services
Post-> Add (Create) Get-> Receive (Read) Put-> Update (Update) Patch-> Update (only certain part) Delete-> Remove (Delete)
HTTPS error codes

                   */

package com.mindera.users.controller;

import com.mindera.users.entity.UserEntity;
import com.mindera.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service = new UserService();

    @GetMapping("/getAll")
    public List<UserEntity> getUsers() throws IOException {
        return service.getUsers();
    }

    @GetMapping("/{userId}")
    public List<UserEntity> getUsersById(@PathVariable Integer userId) throws IOException {
        return service.getUserById(userId);
    }

    @PostMapping("/create")
    public void addUser(@RequestBody UserEntity user) throws IOException {
        service.addUser(user);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Integer userId, @RequestBody UserEntity updatedUser) throws IOException {
        service.updateUser(userId, updatedUser);
    }

    @PatchMapping("/username/{userId}")
    public void updateUsername(@PathVariable Integer userId, @RequestBody UserEntity updatedUsername) throws IOException {
        service.updateUsername(userId, updatedUsername);
    }

    @PatchMapping("/password/{userId}")
    public void updateUserPassword(@PathVariable Integer userId, @RequestBody UserEntity updatedUserPassword) throws IOException {
        service.updateUserPassword(userId, updatedUserPassword);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) throws IOException {
        service.deleteUser(userId);
    }
}


