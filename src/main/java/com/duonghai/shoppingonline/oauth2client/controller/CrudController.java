package com.duonghai.shoppingonline.oauth2client.controller;

import com.duonghai.shoppingonline.dto.UserDTO;
import com.duonghai.shoppingonline.oauth2client.config.WelcomeClient;
import com.duonghai.shoppingonline.oauth2client.service.ClientUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CrudController {

    private final WelcomeClient welcomeClient;
    @Autowired
    private ClientUserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> list = userService.findActiveUsers();
        String welcome = welcomeClient.getUsers();
        return ResponseEntity.ok(welcome+"<br>"+list);
    }

    @PostMapping("/users")
    public String postUser(@Valid UserDTO user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        UserDTO saveUser = userService.saveUser(user);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.findActiveUsers());
        return "index";
    }

    @GetMapping("/create-form")
    public String showAddUserForm(Model model) {
        UserDTO user = UserDTO.builder().build();
        model.addAttribute("user", user);
        return "add-user";
    }

    @GetMapping("/update-form/{id}")
    public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
        UserDTO user = userService.findById(id);
        if(user != null) {
            model.addAttribute("user", user);
        }
        return "update-user";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid UserDTO user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        userService.saveUser(user);
        return "redirect:/index";
    }
}
