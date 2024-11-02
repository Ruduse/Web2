mvn clean install
run project
mvn spring-boot:run


kết nối database:
----chỉnh sửa file trong pom.xml:
<dependency>
<groupId>com.h2database</groupId>
<artifactId>h2</artifactId>
<scope>runtime</scope>
</dependency>
---file application.propertíes
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.password=sa
spring.h2.console.enabled=true
--chạy
---truy cập http://localhost:8080/h2-console


--day03
-- Taọ file user trong service/user
package org.example.day1.service;

import jakarta.persistence.*;

@Entity
@Table(name = "USER_DEMO")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

        private String firstName;
        private String lastName;

        // Getters và Setters
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }


--Tạo file UserRepository
package org.example.day1.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
List<User> findAll();
}
---Taạo file UserService
package org.example.day1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
@Autowired
UserRepository userRepository;

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
---taọ file UserController
package org.example.day1.service;
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
-- tạp file index.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Quản lý Người dùng</title>
</head>
<body>
<h1>Thêm Người dùng</h1>
<form action="#" th:action="@{/users/addUser}" th:object="${user}" method="post">
    <label for="firstName">Họ:</label>
    <input type="text" id="firstName" th:field="*{firstName}" required/>
    <br/>
    <label for="lastName">Tên:</label>
    <input type="text" id="lastName" th:field="*{lastName}" required/>
    <br/>
    <button type="submit">Thêm Người dùng</button>
</form>

<h2>Danh sách Người dùng</h2>
<ul>
    <li th:each="user : ${users}">
        ID: <span th:text="${user.id}"></span>, Họ: <span th:text="${user.firstName}"></span>, Tên: <span th:text="${user.lastName}"></span>
    </li>
</ul>
</body>
</html>

---update dependency trong pom.xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
---chạy terminal:
mvn clean install
---update file application.properties
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=create-drop


----chaỵ file trên cổng
http://localhost:8080/users/
---truy cap de kiem tra csdl h2
 http://localhost:8080/h2-console