package ro.jademy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.jademy.demo.exception.UserNotFoundException;
import ro.jademy.demo.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", getUsers());

        return "users"; // searches for a users.html template page in resources/templates
    }

    @GetMapping("/users/{id}")
    public String getUserDetails(@PathVariable("id") Long id, Model model) {

        Optional<User> userOpt = getUsers().stream().filter(user -> user.getId().equals(id)).findFirst();

        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());

            return "usersDetails";
        } else {
            // should throw 404
            throw new UserNotFoundException("User with ID " + id + " not found!");
        }
    }

    private static List<User> getUsers() {
        User user1 = new User(1L, "Ion", "Ionescu", "ion@example.com", "123456789", "Str. 1");
        User user2 = new User(2L, "Gigi", "Gigescu", "gigi@example.com", "9876543213", "Str. 2");
        User user3 = new User(3L, "Maria", "Marinescu", "maria@example.com", "2345675432", "Str. 3");

        return Arrays.asList(user1, user2, user3);
    }
}
