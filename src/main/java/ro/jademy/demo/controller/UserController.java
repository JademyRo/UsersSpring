package ro.jademy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.jademy.demo.exception.UserNotFoundException;
import ro.jademy.demo.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private static final List<User> USERS = new ArrayList<>();

    static {
        User user1 = new User(1L, "Ion", "Ionescu", "ion@example.com", "123456789", "Str. 1");
        User user2 = new User(2L, "Gigi", "Gigescu", "gigi@example.com", "9876543213", "Str. 2");
        User user3 = new User(3L, "Maria", "Marinescu", "maria@example.com", "2345675432", "Str. 3");

        USERS.addAll(Arrays.asList(user1, user2, user3));
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", USERS);

        return "users"; // searches for a users.html template page in resources/templates
    }

    @GetMapping("/users/{id}")
    public String getUserDetails(@PathVariable("id") Long id, Model model) {

        Optional<User> userOpt = USERS.stream().filter(user -> user.getId().equals(id)).findFirst();

        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());

            return "usersDetails";
        } else {
            // should throw 404
            throw new UserNotFoundException("User with ID " + id + " not found!");
        }
    }

    @GetMapping("/users/create")
    public String showCreateUser(Model model) {
        model.addAttribute("user", new User());

        return "editUser";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user) {
        user.setId(4L);
        USERS.add(user);

        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditUser(@PathVariable("id") Long id, Model model) {
        Optional<User> userOpt = USERS.stream().filter(user -> user.getId().equals(id)).findFirst();

        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());

            return "editUser";
        } else {
            // should throw 404
            throw new UserNotFoundException("User with ID " + id + " not found!");
        }
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute User user) {

        USERS.removeIf(oldUser -> oldUser.getId().equals(id));
        USERS.add(user);

        return "redirect:/users";
    }
}
