package org.example.day1.controller;

import org.example.day1.models.User;
import org.example.day1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NguoiDungController {

    // Phương thức GET trả về trang addUser.html
    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "pages/addUser";
    }

    // Phương thức POST để xử lý thông tin submit từ form
    @PostMapping("/add-user")
    public String submitUser(@ModelAttribute User user, Model model) {
        // Ở đây bạn có thể xử lý thông tin của người dùng, lưu vào cơ sở dữ liệu hoặc xử lý tùy ý
        model.addAttribute("userName", user.getName());
        model.addAttribute("userEmail", user.getEmail());

        // Chuyển tiếp tới trang kết quả (có thể là user.html để hiển thị chi tiết người dùng)
        return "user";
    }
}

//@Controller
//public class NguoiDungController {
////     Phương thức GET trả về trang user.html
//    @GetMapping("/user")
//    public String trangChiTiet(Model model) {
//        model.addAttribute("userName", "Nguyen Van A");
//        return "user";
//    }}