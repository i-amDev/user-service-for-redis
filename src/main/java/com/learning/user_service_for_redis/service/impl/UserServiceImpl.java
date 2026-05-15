package com.learning.user_service_for_redis.service.impl;

import com.learning.user_service_for_redis.dto.UserDto;
import com.learning.user_service_for_redis.dto.UserResponse;
import com.learning.user_service_for_redis.entity.User;
import com.learning.user_service_for_redis.repository.UserRepository;
import com.learning.user_service_for_redis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(userDto.getAge())
                .city(userDto.getCity())
                .country(userDto.getCountry())
                .build();

        User entity = userRepository.save(user);
        simulateSlowDbCall();
        return mapToUserResponse(entity);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> entities = userRepository.findAll();
        simulateSlowDbCall();
        return entities.stream()
                .map(entity -> mapToUserResponse(entity))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist by id " + id));
        simulateSlowDbCall();
        return mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist by id " + id));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());

        User entity = userRepository.save(user);

        simulateSlowDbCall();
        return mapToUserResponse(entity);
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist by id " + id));

        userRepository.deleteById(id);

        simulateSlowDbCall();
        return "User deleted successfully!!";
    }

    private UserResponse mapToUserResponse(User entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .age(entity.getAge())
                .city(entity.getCity())
                .country(entity.getCountry())
                .build();
    }

    private void simulateSlowDbCall() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
