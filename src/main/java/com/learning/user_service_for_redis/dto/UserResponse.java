package com.learning.user_service_for_redis.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String country;
}
