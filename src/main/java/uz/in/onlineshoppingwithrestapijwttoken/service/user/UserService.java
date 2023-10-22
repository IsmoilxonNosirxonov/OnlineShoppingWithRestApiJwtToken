package uz.in.onlineshoppingwithrestapijwttoken.service.user;

import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.SignInDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.UserCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.UserReadDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.response.AuthenticationResponse;
import java.util.List;
import java.util.UUID;

@Service
public interface UserService{

    AuthenticationResponse save(UserCreateDto userCreateDto);
    AuthenticationResponse signIn(SignInDto signInDto);
    UserReadDto getById(UUID id);
    List<UserReadDto> getAll();
    UserReadDto update(UserCreateDto createDto, UUID id);
    void delete(UUID id);

}
