package com.supership.ship.service;

import com.supership.ship.dto.UserDTO;
import com.supership.ship.request.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    UserDTO save(UserDTO userDTO);
    UserDTO login(String userName, String password);
    UserDTO register(RegisterRequest registerRequest);

}

