package com.supership.ship.service;

import com.supership.ship.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    UserDTO save(UserDTO userDTO);

}

