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

    private String password;

    private String fullName;

    private String email;

    private Integer isActived;

    private List<String> roleCode;

}
