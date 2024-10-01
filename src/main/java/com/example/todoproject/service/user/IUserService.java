package com.example.todoproject.service.user;

import com.example.todoproject.dto.UserDto;
import com.example.todoproject.model.TodoItem;
import com.example.todoproject.model.User;
import com.example.todoproject.request.AddUserRequest;

import java.util.List;

public interface IUserService {
    User addUser(AddUserRequest userRequest);
    List<User> getAllUsers();
    User getUserByUserId(Long id);
    void deleteUserById(Long id);
    /*void updateUserById();*/
    void assignTodoItemById(Long todoitem_id, Long user_id);
    UserDto converToDto(User user);
}
