package com.example.cinema.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public List <User> getUsers (){return userRepository.findAll();}

    public User getUser(String id){
        Long user_id = Long.parseLong(id);
        return userRepository.findById(user_id).orElse(null);
    }

    public User createUser(User user){return userRepository.save(user);}

    public void deleteUser(String id){
        Long user_id = Long.parseLong(id);
        userRepository.deleteById(user_id);
    }

    public void updateUser(String id, User data){
        Long user_id = Long.parseLong(id);
        User user = userRepository.findById(user_id).orElse(null);

        if (user != null){
            user.setName(data.getName());
            user.setEmail(data.getEmail());
            user.setPassword(data.getPassword());
            user.setRole(data.getRole());
            user.setAge(data.getAge());
            userRepository.save(user);
        }
    }

    public ResponseEntity<?> login(LoginForm loginForm)
    {
        if(userRepository.findByEmail(loginForm.getEmail()) != null)
        {
            if(loginForm.getPassword().equals(userRepository.findByEmail(loginForm.getEmail()).getPassword()))
            {
                return ResponseEntity.ok().body(userRepository.findByEmail(loginForm.getEmail()));
            }
            else
            {
                return ResponseEntity.status(404).body("Wrong Password");
            }
        }
        else{
            return ResponseEntity.status(404).body("User Not found");
        }
    }
}
