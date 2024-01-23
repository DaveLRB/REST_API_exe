package com.mindera.users.services;


import com.mindera.users.entity.UserEntity;
import org.springframework.stereotype.Service;
import com.mindera.users.repository.UserRepository;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository = new UserRepository();


    public List<UserEntity> getUsers() throws IOException {
        return repository.getUsers();
    }

    public List<UserEntity> getUserById(Integer userId) throws IOException {
        return repository.getUserById(userId);
    }

    public void addUser(UserEntity user) throws IOException {
        repository.addUser(user);
    }

    public void updateUser(Integer userId, UserEntity updateUser) throws IOException {
        repository.updateUser(userId, updateUser);
    }

    public void updateUsername(Integer userId, UserEntity updateUsername) throws IOException {
        repository.updateUsername(userId, updateUsername);
    }

    public void updateUserPassword(Integer userId, UserEntity updateUserPassword) throws IOException {
        repository.updateUserPassword(userId, updateUserPassword);
    }

    public void deleteUser(Integer userId) throws IOException {
        repository.deleteUser(userId);
    }
}
