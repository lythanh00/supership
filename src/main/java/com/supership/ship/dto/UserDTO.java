package com.supership.ship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends AbstractDTO<UserDTO>{

    private String userName;

    private String password; // k co

    private String fullName;

    private String email;

    private Integer isActived; // k co

    private List<String> roleCode;

    // Constructor có tham số
    public UserDTO(Long id, String userName, String fullName, String email) {
        super(id); // Gọi constructor của lớp cha với tham số id
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
    }

}
