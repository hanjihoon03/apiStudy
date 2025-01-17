package com.example.study_api.controller;

import com.example.study_api.dto.UserRequestDto;
import com.example.study_api.dto.UserResponseDto;
import com.example.study_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody @Valid UserRequestDto userRequestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "잘못된 입력 값 입니다.";
        }
        userService.signUp(userRequestDto);
        return "회원가입 되었습니다.";
    }

    @GetMapping("/info/{username}")
    public UserResponseDto getInfo(@PathVariable String username) {
        return userService.getInfo(username);
    }
}
