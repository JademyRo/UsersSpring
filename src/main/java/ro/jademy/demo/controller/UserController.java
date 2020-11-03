package ro.jademy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.jademy.demo.model.User;

import java.util.Arrays;

@Controller
public class UserController {

    @GetMapping("/users")
    public String getUsers(Model model) {

        User user1 = new User("Ion", "Ionescu", "ion@example.com");
        User user2 = new User("Gigi", "Gigescu", "gigi@example.com");
        User user3 = new User("Marinescu", "Marinescu", "maria@example.com");

        model.addAttribute("users", Arrays.asList(user1, user2, user3));

        return "users"; // searches for a users.html template page in resources/templates
    }


}
