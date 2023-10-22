package uz.in.onlineshoppingwithrestapijwttoken.service.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.config.JwtService;
import uz.in.onlineshoppingwithrestapijwttoken.domain.Enum.UserRole;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.SignInDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.UserCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.UserReadDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.User;
import uz.in.onlineshoppingwithrestapijwttoken.domain.response.AuthenticationResponse;
import uz.in.onlineshoppingwithrestapijwttoken.exception.DataNotFoundException;
import uz.in.onlineshoppingwithrestapijwttoken.exception.DublicateValueException;
import uz.in.onlineshoppingwithrestapijwttoken.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse save(UserCreateDto userCreateDto) {
        if(userRepository.findByEmail(userCreateDto.getEmail()).isPresent()){
            throw new DublicateValueException("This email already exists: "+userCreateDto.getEmail());
        }
        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        User user = userRepository.save(modelMapper.map(userCreateDto, User.class));
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse signIn(SignInDto signInDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDto.getEmail(),
                        signInDto.getPassword()
                )
        );
        User user = userRepository.findByEmail(signInDto.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public UserReadDto getById(UUID id) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isEmpty()){
            throw new DataNotFoundException("User not found");
        }
        return modelMapper.map(byId.get(), UserReadDto.class);
    }

    @Override
    public List<UserReadDto> getAll() {
        List<UserReadDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (!user.getRole().equals(UserRole.ADMIN)) {
                users.add(modelMapper.map(user, UserReadDto.class));
            }
        }
        return users;
    }

    @Override
    public UserReadDto update(UserCreateDto createDto, UUID id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setFullName(createDto.getFullName());
            user.setEmail(createDto.getEmail());
            user.setPassword(passwordEncoder.encode(createDto.getPassword()));
            user.setPhoneNumber(createDto.getPhoneNumber());
            user.setBalance(createDto.getBalance());
            userRepository.save(user);
            return modelMapper.map(byId.get(), UserReadDto.class);
        }
        return modelMapper.map(createDto, UserReadDto.class);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
