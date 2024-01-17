package com.mindera.users.services;


import com.mindera.users.entity.UserEntity;
import org.springframework.stereotype.Service;
import com.mindera.users.repository.UserRepository;

import java.io.IOException;
import java.util.List;
@Service
public class UserService {

   public final UserRepository repository = new UserRepository();


    public List<UserEntity> getUsers() throws IOException {
        return repository.getUsers();
    }

    public void addUser(final UserEntity user) throws IOException {
        repository.addUser(user);
    }

    public void updateUser(Long userId, UserEntity updateUser) throws IOException {
        repository.updateUser(userId,updateUser);
    }

    public void deleteUser(Long userId) throws IOException {
        repository.deleteUser(userId);
    }
}
