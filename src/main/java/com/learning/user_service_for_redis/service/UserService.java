package com.learning.user_service_for_redis.service;

import com.learning.user_service_for_redis.dto.UserDto;
import com.learning.user_service_for_redis.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponse createUser(UserDto userDto);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserDto userDto);

    String deleteUser(Long id);
}
