package com.example.todoproject.service.user;

import com.example.todoproject.dto.UserDto;
import com.example.todoproject.model.TodoItem;
import com.example.todoproject.model.User;
import com.example.todoproject.repository.TodoItemRepository;
import com.example.todoproject.repository.UserRepository;
import com.example.todoproject.request.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final TodoItemRepository todoItemRepository;


    @Override
    public User addUser(AddUserRequest userRequest) {

        User user = createUser(userRequest);

        return userRepository.save(user);
    }

    public User createUser(AddUserRequest request){
        return new User(
                request.getName(),
                request.getSurname()
        );
    }


    @Override
    public List<User> getAllUsers() { return userRepository.findAll();}

    @Override
    public User getUserByUserId(Long id) { return userRepository.getReferenceById(id);}

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public void assignTodoItemById(Long todoitem_id, Long user_id) {
        TodoItem todoItem = todoItemRepository.getReferenceById(todoitem_id);
        User user = userRepository.getReferenceById(user_id);

        user.assignTodoItem(todoItem);
        todoItem.assignUser(user);

        userRepository.save(user);
        todoItemRepository.save(todoItem);
    }

    @Override
    public UserDto converToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());

        return userDto;
    }


}
