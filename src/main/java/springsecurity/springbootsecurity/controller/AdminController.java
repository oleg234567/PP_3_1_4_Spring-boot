package springsecurity.springbootsecurity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.services.RoleService;
import springsecurity.springbootsecurity.services.UserService;


import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;



    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping()
    public String showAllUsers(@ModelAttribute("user") User user, Principal principal, Model model) {
        User authenticatedUser = userService.findByUsername(principal.getName());
        model.addAttribute("authenticatedUser", authenticatedUser);
        model.addAttribute("rolesAuthenticatedUser", authenticatedUser.getRoles());
        model.addAttribute("users", userService.findAllUsers());
        List<Role> roles = roleService.findAllRole();
        model.addAttribute("allRoles", roles);
        return "/admin";
    }

    @PostMapping("")
    public String saveUser(@ModelAttribute("user")  User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user")  User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}