package com.supership.ship.api;


import com.supership.ship.dto.ResponseDTO;
import com.supership.ship.dto.UserDTO;
import com.supership.ship.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
//@RequestMapping("/user")
public class UserAPI {

    @Autowired
    private IUserService userService;




//    @GetMapping("/search")
//    public ResponseEntity<?> searchUser(@RequestParam(name = "keyword", required = false, defaultValue = "") String name){
//        List<UserDTO> users = userService.searchUser(name);
//        return  ResponseEntity.ok(users);
//    }
//
//    @GetMapping("")
//    public ResponseEntity<?> getListUser(){
//        List<UserDTO> users = userService.getListUser();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable int id){
//        UserDTO result = userService.getUserById(id);
//        return ResponseEntity.ok(result);
//    }



    @PostMapping(value = "/user")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO model){
        UserDTO userDTO = userService.save(model);
        if (userDTO != null) {
            return new ResponseEntity<>(new ResponseDTO(200, userDTO, "User created successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(500, null, "Failed to create user"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO model, @PathVariable("id") long id){
        model.setId(id);
        UserDTO userDTO = userService.save(model);
        if (userDTO != null) {
            return new ResponseEntity<>(new ResponseDTO(200, userDTO, "User updated successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(500, null, "Failed to update user"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUser(){
//        return null;
//    }
}