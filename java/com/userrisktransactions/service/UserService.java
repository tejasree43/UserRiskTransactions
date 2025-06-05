package com.userrisktransactions.service;

import com.userrisktransactions.dto.UserDto;
import com.userrisktransactions.model.Role;
import com.userrisktransactions.model.User;
import com.userrisktransactions.repository.RoleRepository;
import com.userrisktransactions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto) {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Consider encoding the password

        user.setFirstName(userDto.getFirstName()); // Consider encoding the password
            user.setLastName(userDto.getLastName()); // Consider encoding the password
            user.setDateOfBirth(userDto.getDateOfBirth()); // Consider encoding the password

        if (userDto.getRoles() != null) {
            Set<Role> roleEntities = userDto.getRoles().stream()
                    .map(roleDto -> roleRepository.findByName(roleDto.getName()))
                    .filter(Objects::nonNull) // Filter out null roles
                    .collect(Collectors.toSet());
            user.setRoles(roleEntities);
        }

            return userRepository.save(user); // Save the user
        }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}

