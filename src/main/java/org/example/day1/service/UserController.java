package org.example.day1.service;//package org.example.day1.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//    @Autowired
//    UserService userService;
//
//    @PostMapping("/addUser")
//    public void saveUser(@ModelAttribute("user") User user) {
//        System.out.println("firstName: " + user.getFirstName());
//        System.out.println("lastName: " + user.getLastName());
//
//        userService.saveOrUpdate(user);
//    }
//
//    @GetMapping("/all")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User()); // Để lưu trữ người dùng mới
        return "index"; // Trả về tệp HTML index.html
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveOrUpdate(user);
        return "redirect:/users/"; // Redirect về trang index sau khi thêm người dùng
    }

}
