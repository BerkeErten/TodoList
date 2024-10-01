package com.example.todoproject.controller;

import com.example.todoproject.dto.UserDto;
import com.example.todoproject.model.User;
import com.example.todoproject.request.AddUserRequest;
import com.example.todoproject.request.AssignRequest;
import com.example.todoproject.response.ApiResponse;
import com.example.todoproject.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Executable;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final IUserService userService;

    @GetMapping("user/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId){
        try {
            User user = userService.getUserByUserId(userId);
            UserDto userDto = userService.converToDto(user);

            return ResponseEntity.ok(new ApiResponse("User retrieved successfully", userDto));
        }catch (Exception e){
            ApiResponse errorResponse = new ApiResponse("Failed to retrieve user", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody AddUserRequest userRequest){
        try{
            User user = userService.addUser(userRequest);

            UserDto userDto = userService.converToDto(user);

            ApiResponse response = new ApiResponse("User saved successfully", userDto);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            ApiResponse errorResponse = new ApiResponse("Failed to add user", null);

            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/assign")
    public ResponseEntity<ApiResponse> assignUser(@RequestBody AssignRequest assignRequest){
        try {
            userService.assignTodoItemById(assignRequest.getTodoitemid(), assignRequest.getUserid());
            return ResponseEntity.ok(new ApiResponse("Todo item assigned", null));
        }catch (Exception e){
            ApiResponse errorResponse = new ApiResponse("Failed to assign todo item", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }


}
