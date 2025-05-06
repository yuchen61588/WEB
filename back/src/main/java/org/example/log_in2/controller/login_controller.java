package org.example.log_in2.controller;

import org.example.log_in2.podo.LoginResponse;
import org.example.log_in2.podo.changePassword;
import org.example.log_in2.podo.registerResponse;
import org.example.log_in2.podo.user;
import org.example.log_in2.service.impl.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")  // 允许前端跨域请求（修改为实际前端地址）
public class login_controller {
    @Autowired
    private AutoService autoService;

    @PostMapping("/register")
    public registerResponse register(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("confirmPassword") String confirmPassword,
                                     @RequestParam("email") String email,
                                     @RequestParam("birthday") String birthday,
                                     @RequestParam("url") String url) {
        user user = new user(username, password, confirmPassword, email, birthday, url);
        return autoService.register(user);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return autoService.login(username, password);
    }
    @PostMapping("/changePassword")
    public changePassword changePassword(@RequestParam("username") String username, @RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword) {
        return autoService.changePassword(username, oldpassword, newpassword);
    }
    @PostMapping("/load")
    public LoginResponse login(@RequestParam("username") String username) {
        return autoService.load(username);
    }
}
