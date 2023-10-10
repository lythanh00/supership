package com.supership.ship.api;

import com.supership.ship.dto.UserDTO;
import com.supership.ship.exception.UserException;
import com.supership.ship.request.LoginRequest;
import com.supership.ship.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginAPI {

    @Autowired
    private IUserService userService;

    @GetMapping
    public String showHomePage(Model model, HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        if (userDTO != null) {
            model.addAttribute("userDTO", userDTO); // Thêm userDTO vào model với attribute name là "userDTO"
        }
        return "index";
    }

    @GetMapping("login")
    public String showLogin(Model model){
        model.addAttribute("loginrequest", new LoginRequest("", ""));
        return "login";
    }

    @PostMapping("login")
    public String handleLogin(@Valid @ModelAttribute("loginrequest") LoginRequest loginRequest, BindingResult result, HttpSession session){
        if (result.hasErrors()){
            return "login";
        }
        UserDTO userDTO;
        try{
            userDTO = userService.login(loginRequest.getUserName(), loginRequest.getPassword());
            if (userDTO != null){
                // Đăng nhập thành công, lưu thông tin người dùng vào phiên của người dùng
                session.setAttribute("userDTO", new UserDTO(userDTO.getId(), userDTO.getUserName(), userDTO.getFullName(), userDTO.getEmail()));
                return "redirect:/";
            }else {
                // Xử lý trường hợp đăng nhập thất bại
                //
            }
        } catch (UserException ex){
            switch (ex.getMessage()){
                case "User is not found":
                    result.addError(new FieldError("loginrequest", "userName", "UserName is not exist"));
                    break;
                case "User is not activated":
                    result.addError(new FieldError("loginrequest", "userName", "UserName is not activated"));
                    break;
                case "Password is incorrect":
                    result.addError(new FieldError("loginrequest", "password", "Password is incorrect"));
                    break;
            }
        }
        return "login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("userDTO");
        return "redirect:/";
    }

    @GetMapping("register")
    public String showRegister(){
        return "register";
    }

    @GetMapping("foo")
    public String foo(){
        throw new UserException("User is not found");
    }



    @GetMapping("admin")
    public String showAdminPage(HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        if (userDTO != null) { // nếu chứa user trả về trang admin
            return "admin";
        } else { // chưa login trả về trang chủ
            return "redirect:/";
        }
    }
}
