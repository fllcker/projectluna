package ru.fllcker.usersservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.usersservice.dto.CreateUserDto;
import ru.fllcker.usersservice.models.User;
import ru.fllcker.usersservice.repositories.IUsersRepository;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final IUsersRepository usersRepository;

    public User create(CreateUserDto createUserDto) {
        User user = User.builder()
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName()).build();

        return usersRepository.save(user);
    }

    public User findById(String id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public User findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }
}
