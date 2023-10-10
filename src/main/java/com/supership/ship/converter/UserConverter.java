package com.supership.ship.converter;

import com.supership.ship.entity.RoleEntity;
import com.supership.ship.entity.UserEntity;
import com.supership.ship.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {
    public UserEntity toEntity(UserDTO dto){
        UserEntity entity = new UserEntity();
        entity.setUserName(dto.getUserName());
//        entity.setPassword(dto.getPassword());
        entity.setPassword(dto.getHashed_pasword());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setIsActived(dto.getIsActived());
        return entity;
    }

    public UserDTO toDTO(UserEntity entity){
        UserDTO dto = new UserDTO();
        if (entity.getId() != null){
            dto.setId(entity.getId());
        }
        dto.setUserName(entity.getUserName());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setIsActived(entity.getIsActived());
        List<String> roleCodes = new ArrayList<>();
        for (RoleEntity role : entity.getRoles()) {
            roleCodes.add(role.getCode());
        }
        dto.setRoleCode(roleCodes);
        return dto;
    }

    public UserEntity toEntity(UserDTO dto, UserEntity entity){
        entity.setUserName(dto.getUserName());
//        entity.setPassword(dto.getPassword());
        entity.setPassword(dto.getHashed_pasword());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setIsActived(dto.getIsActived());
        return entity;
    }
}
