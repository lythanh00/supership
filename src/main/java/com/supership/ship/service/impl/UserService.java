package com.supership.ship.service.impl;

import com.supership.ship.converter.UserConverter;
import com.supership.ship.dto.UserDTO;
import com.supership.ship.entity.RoleEntity;
import com.supership.ship.entity.UserEntity;
import com.supership.ship.repository.RoleRepository;
import com.supership.ship.repository.UserRepository;
import com.supership.ship.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        if (userDTO.getId() != null){
            Optional<UserEntity> userEntityOptional = userRepository.findById(userDTO.getId());
            UserEntity oldUserEntity = userEntityOptional.orElse(null);
            userEntity = userConverter.toEntity(userDTO, oldUserEntity);
        } else {
            
            // userentity.id = userdto.role +  userdto.username
            userEntity = userConverter.toEntity(userDTO);
        }

        List<String> roleCodes = userDTO.getRoleCode();
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (String roleCode : roleCodes) {
            RoleEntity roleEntity = roleRepository.findOneByCode(roleCode);
            if (roleEntity != null) {
                roleEntities.add(roleEntity);
            } else {
                // Xử lý trường hợp không tìm thấy roleEntity nếu cần
            }
        }
        userEntity.setRoles(roleEntities);
        userEntity = userRepository.save(userEntity);

        return userConverter.toDTO(userEntity);
    }

}
