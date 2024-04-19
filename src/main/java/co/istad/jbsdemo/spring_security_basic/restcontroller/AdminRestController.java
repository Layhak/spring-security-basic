package co.istad.jbsdemo.spring_security_basic.restcontroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminRestController {
    @GetMapping
    public String getAllUser() {
        return "There are all the users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        return  "delete user with id "+id;
    }
}
