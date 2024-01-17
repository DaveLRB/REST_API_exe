package com.mindera.users.repository;


import com.mindera.users.entity.UserEntity;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final String FILE_PATH = "/Users/mindera/Documents/REST_API_exe/users/src/main/resources/userdatabase.txt";

    private final List<UserEntity> users = new LinkedList<>();

    public List<UserEntity> getUsers() throws IOException {
        List<UserEntity> users = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    Long id = Long.parseLong(parts[0]);
                    String username = parts[1];
                    String password = parts[2];

                    UserEntity user = new UserEntity();
                    user.setId(id);
                    user.setUsername(username);
                    user.setPassword(password);

                    users.add(user);
                } else {
                    System.out.println("Invalid format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void addUser(final UserEntity user) throws IOException {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.format("%d,%s,%s", user.getId(), user.getUsername(), user.getPassword());
            write.write(line);
            users.add(user);
        }
    }

    public void updateUser(Long userId, UserEntity updatedUser) throws IOException {

        List<UserEntity> allUsers = getUsers();
        Optional<UserEntity> userToUpdate = allUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();

        userToUpdate.ifPresent(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (UserEntity user : allUsers) {
                String line = String.format("%d,%s,%s", user.getId(), user.getUsername(), user.getPassword());
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Long userId) throws IOException {
        List<UserEntity> allUsers = getUsers();
        Optional<UserEntity> userToDelete = allUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
        userToDelete.ifPresent(allUsers::remove);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (UserEntity user : allUsers) {
                String line = String.format("%d,%s,%s", user.getId(), user.getUsername(), user.getPassword());
                writer.write(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}