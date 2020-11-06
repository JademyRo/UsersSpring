package ro.jademy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.jademy.demo.exception.UserNotFoundException;
import ro.jademy.demo.model.User;
import ro.jademy.demo.repository.UserRepository;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "users"; // searches for a users.html template page in resources/templates
    }

    @GetMapping("/users/{id}")
    public String getUserDetails(@PathVariable("id") Long id, Model model) {

        Optional<User> userOpt = userRepository.findById(id);

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
        userRepository.save(user);

        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditUser(@PathVariable("id") Long id, Model model) {
        Optional<User> userOpt = userRepository.findById(id);

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

        userRepository.save(user);

        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        userRepository.deleteById(id);

        return "redirect:/users";
    }
}
