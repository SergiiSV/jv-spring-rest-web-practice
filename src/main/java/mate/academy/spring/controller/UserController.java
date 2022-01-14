package mate.academy.spring.controller;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.response.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.dto.mapping.DtoResponseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final DtoResponseMapper<UserResponseDto, User> dtoResponseMapper;
    private final UserService userService;

    public UserController(DtoResponseMapper<UserResponseDto, User> dtoResponseMapper,
                          UserService userService) {
        this.dtoResponseMapper = dtoResponseMapper;
        this.userService = userService;
    }

    @GetMapping("/by-email")
    public UserResponseDto findByEmail(@RequestParam String email) {
        return dtoResponseMapper.toDto(userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Can't find user by email: " + email)));
    }
}