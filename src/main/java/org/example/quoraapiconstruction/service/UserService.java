package org.example.quoraapiconstruction.service;

import org.example.quoraapiconstruction.dto.UserRequestDTO;
import org.example.quoraapiconstruction.dto.UserResponseDTO;
import org.example.quoraapiconstruction.entity.User;
import org.example.quoraapiconstruction.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

//    public User createUser(User user){
//        return userRepository.save(user);
//    }

    public UserResponseDTO createUser(UserRequestDTO request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setBio(request.getBio());

        User savedUser = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setBio(savedUser.getBio());

        return response;
    }

}
