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



    @GetMapping("/get")
	public List<UserEntity> getUsers() throws IOException {
		return service.getUsers();
	}
	@PostMapping("/add")
	public void addUser(@RequestBody final UserEntity user) throws IOException {
		service.addUser(user);
	}
	@PutMapping("/update")
	public void updateUser(Long userId, UserEntity updatedUser)throws IOException{
		service.updateUser(userId, updatedUser);
	}

	@DeleteMapping("/delete")
	public void delete(Long userId) throws IOException {
		service.deleteUser(userId);
    }









}

