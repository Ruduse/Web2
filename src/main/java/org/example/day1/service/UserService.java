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
    public void delete(User user) {
            
            userRepository.delete(user);
    }
    public void save(User user) {
        userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
