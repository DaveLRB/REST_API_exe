package com.mindera.users.repository;


import com.mindera.users.entity.UserEntity;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                    Integer id = Integer.parseInt(parts[0]);
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
            System.out.println(e.getMessage());
            throw new IOException("Error reading users from file", e);
        }
        return users;
    }

    public List<UserEntity> getUserById(Integer userId) throws IOException {
        try {
            List<UserEntity> allUsers = getUsers();
            List<UserEntity> userById = allUsers.stream()
                    .filter(user -> user.getId()
                            .equals(userId))
                    .collect(Collectors.toList());
            return userById;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException("Error reading user from file", e);
        }
    }

    public void addUser(UserEntity user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.format("%d,%s,%s", user.getId(), user.getUsername(), user.getPassword());
            writer.write(line);
            writer.newLine();
            users.add(user);
        } catch (IOException e){
            System.out.println(e.getMessage());
            throw new IOException("Error while creating user", e);
        }
    }

    public void updateUser(Integer userId, UserEntity updatedUser) throws IOException {

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
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException("Error updating user", e);
        }
    }

    public void updateUsername(Integer userId, UserEntity updateUsername) throws IOException {
        List<UserEntity> allUsers = getUsers();
        Optional<UserEntity> userToUpdate = allUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();

        userToUpdate.ifPresent(user -> {
            user.setUsername(updateUsername.getUsername());
        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (UserEntity user : allUsers) {
                String line = String.format("%d,%s,%s", user.getId(), user.getUsername(), user.getPassword());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException("Error updating username", e);
        }
    }

    public void updateUserPassword(Integer userId, UserEntity updatedUserPassword) throws IOException {

        List<UserEntity> allUsers = getUsers();
        Optional<UserEntity> userToUpdate = allUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();

        userToUpdate.ifPresent(user -> {
            user.setPassword(updatedUserPassword.getPassword());
        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (UserEntity user : allUsers) {
                String line = String.format("%d,%s,%s", user.getId(), user.getUsername(), user.getPassword());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException("Error updating user Password", e);
        }
    }


    public void deleteUser(Integer userId) throws IOException {
        List<UserEntity> allUsers = getUsers();
        Optional<UserEntity> userToDelete = allUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
        userToDelete.ifPresent(allUsers::remove);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (UserEntity user : allUsers) {
                String line = String.format("%d,%s,%s", user.getId(), user.getUsername(), user.getPassword());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException("Error while deleting user", e);
        }
    }

}