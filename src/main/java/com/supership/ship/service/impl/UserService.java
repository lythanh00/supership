package com.supership.ship.service.impl;

import com.supership.ship.converter.UserConverter;
import com.supership.ship.dto.UserDTO;
import com.supership.ship.entity.RoleEntity;
import com.supership.ship.entity.UserEntity;
import com.supership.ship.exception.UserException;
import com.supership.ship.hash.Hashing;
import com.supership.ship.repository.RoleRepository;
import com.supership.ship.repository.UserRepository;
import com.supership.ship.request.RegisterRequest;
import com.supership.ship.service.IUserService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Hashing hash;


    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        if (userDTO.getId() != null){ // update
            Optional<UserEntity> userEntityOptional = userRepository.findById(userDTO.getId());
            UserEntity oldUserEntity = userEntityOptional.orElse(null);
            if (oldUserEntity != null) {
                // Kiểm tra xem mật khẩu đã thay đổi chưa
                if (!userDTO.getPassword().equals(oldUserEntity.getPassword())) {
                    // Mật khẩu đã thay đổi, băm mật khẩu mới
                    userDTO.setHashed_password(hash.hashPassword(userDTO.getPassword()));
                }

                userEntity = userConverter.toEntity(userDTO, oldUserEntity);
            } else {
                // Xử lý tình huống khi không tìm thấy người dùng để cập nhật
                // (có thể làm gì đó tùy theo yêu cầu của bạn)
            }
        } else { // create
            userDTO.setIsActived(1);
            // bam password
            userDTO.setHashed_password(hash.hashPassword(userDTO.getPassword()));
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

    @Override
    public UserDTO login(String userName, String password) {
        Optional<UserEntity> o_userEntity = Optional.ofNullable(userRepository.findByUserName(userName));
        //check username
        if (!o_userEntity.isPresent()) {
            throw new UserException("User is not found");
        }
        UserEntity userEntity = o_userEntity.get();
        // check isActived
        if (userEntity.getIsActived() == 0){
            throw new UserException("User is not activated");
        }
        //check password
        String storedPassword = userEntity.getPassword(); // Lấy mật khẩu đã lưu trữ
        if (hash.validatePassword(password, storedPassword)) {
            // Mật khẩu khớp, đăng nhập thành công
            // Trả về thông tin người dùng hoặc thực hiện các thao tác khác tùy theo yêu cầu của bạn
            return userConverter.toDTO(userEntity);
        } else{
            throw new UserException("Password is incorrect");
        }
    }

    @Override
    public UserDTO register(RegisterRequest registerRequest) {
        // Kiểm tra xem tên người dùng đã tồn tại chưa
        if (userRepository.existsByUserName(registerRequest.getUserName())) {
            throw new UserException("Username is already exist");
        }

        // Kiểm tra xem mật khẩu và xác nhận mật khẩu có khớp nhau không
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new UserException("Password confirmation does not match");
        }

        // Tạo đối tượng UserEntity từ thông tin đăng ký
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(registerRequest.getUserName());
        userEntity.setFullName(registerRequest.getFullName());
        userEntity.setPhoneNumber(registerRequest.getPhoneNumber());
        userEntity.setIsActived(1); // Mặc định là không kích hoạt, người dùng cần xác nhận email để kích hoạt

        // Băm mật khẩu trước khi lưu vào cơ sở dữ liệu
        String hashedPassword = hash.hashPassword(registerRequest.getPassword());
        userEntity.setPassword(hashedPassword);

        // Lưu người dùng mới vào cơ sở dữ liệu
        userEntity = userRepository.save(userEntity);

        // Trả về thông tin người dùng đã đăng ký
        return userConverter.toDTO(userEntity);
    }

}
