package se2.hanu_hospital.controllers;

import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.models.User;

import java.util.ArrayList;
import java.util.Iterator;

@RestController
public class UserController {

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public String greeting(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("admin", "123456"));
        users.add(new User("ml", "123"));
        users.add(new User("aaa", "bbb"));

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User curr = (User) iterator.next();
            if (curr.getUsername().equals(username) && curr.getPassword().equals(password)) {
                return "{\"isAuthen\": true}";
            }
        }

        return "{\"isAuthen\": false}";
    }

    @GetMapping("/")
    public String a() {
        return "Adu";
    }
}
