package com.learning.user_service_for_redis.dto;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String country;
}
