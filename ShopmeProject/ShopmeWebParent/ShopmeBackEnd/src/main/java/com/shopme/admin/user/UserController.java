package com.shopme.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {
    @GetMapping("/users")
    public String listAll(){
        return "users";
    }
}
