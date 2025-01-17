package com.example.study_api.service;

import com.example.study_api.dto.UserRequestDto;
import com.example.study_api.dto.UserResponseDto;
import com.example.study_api.entity.User;
import com.example.study_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);
        userRepository.save(user);
    }

    public UserResponseDto getInfo(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("사용자를 찾지 못했습니다."));
        return new UserResponseDto(user);
    }
}
