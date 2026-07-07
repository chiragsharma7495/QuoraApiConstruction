package org.example.quoraapiconstruction.service;

import org.example.quoraapiconstruction.dto.UserRequestDTO;
import org.example.quoraapiconstruction.dto.UserResponseDTO;
import org.example.quoraapiconstruction.entity.User;
import org.example.quoraapiconstruction.exception.EmailAlreadyExistsException;
import org.example.quoraapiconstruction.exception.UserNotFoundException;
import org.example.quoraapiconstruction.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private UserResponseDTO mapToResponseDTO(User user) {

        UserResponseDTO response = new UserResponseDTO();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setBio(user.getBio());

        return response;
    }


    public UserResponseDTO createUser(UserRequestDTO request){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email Already Exist");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setBio(request.getBio());

        User savedUser = userRepository.save(user);

        return mapToResponseDTO(savedUser);
    }

    public List<UserResponseDTO> getAllUsers(){
        List<User> users = userRepository.findAll();

        List<UserResponseDTO> responseList = new ArrayList<>();

        for(User user : users){
            responseList.add(mapToResponseDTO(user));
        }

        return responseList;
    }

    public UserResponseDTO getUserById(Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("user not found with this id : " + id);
        }

        User user = optionalUser.get();

        return mapToResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id , UserRequestDTO request){

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        if(!user.getEmail().equals(request.getEmail())
           && userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exception");
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setBio(request.getBio());

        User updatedUser = userRepository.save(user);
        return mapToResponseDTO(updatedUser);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)) throw new UserNotFoundException("user not found");
        userRepository.deleteById(id);
    }

}
