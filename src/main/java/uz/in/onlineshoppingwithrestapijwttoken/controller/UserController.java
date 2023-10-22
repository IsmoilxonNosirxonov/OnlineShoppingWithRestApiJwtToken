package uz.in.onlineshoppingwithrestapijwttoken.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.SignInDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.UserCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.UserReadDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.User;
import uz.in.onlineshoppingwithrestapijwttoken.domain.response.AuthenticationResponse;
import uz.in.onlineshoppingwithrestapijwttoken.service.user.UserService;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/sign-up")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody UserCreateDto createDto) {
        return ResponseEntity.ok(userService.save(createDto));
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<AuthenticationResponse> signIn(
            @RequestBody SignInDto signInDto
    ) {
        return ResponseEntity.ok(userService.signIn(signInDto));
    }

    @DeleteMapping("/admin/delete-user/{id}")
    public ResponseEntity<User> deleteById(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/update-user/{id}")
    public ResponseEntity<UserReadDto> update(@RequestBody UserCreateDto createDto, @PathVariable UUID id) {
        return ResponseEntity.ok(userService.update(createDto, id));
    }

    @GetMapping("/admin/get-user/{id}")
    public ResponseEntity<UserReadDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
}

