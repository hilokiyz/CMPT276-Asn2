package sfu.cmpt276.asn2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import sfu.cmpt276.asn2.models.User;
import sfu.cmpt276.asn2.models.UserRepository;

@Controller
public class UserControllers {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("getting all users");

        // get list from database
        List<User> users = userRepo.findAll();
        // end of database call

        // convert userlist to model to put into showAll
        model.addAttribute("userlist", users);
        return "users/showAll";
    }

    @PostMapping("/users/addUser")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response){
        System.out.println("Added user");
        String newName = newuser.get("name");
        int newWeight = Integer.parseInt(newuser.get("weight"));
        int newHeight = Integer.parseInt(newuser.get("height"));
        String newHairColor = newuser.get("hairColor");
        double newGPA = Double.parseDouble(newuser.get("GPA"));
        userRepo.save(new User(newName,newWeight,newHeight,newHairColor,newGPA));

        response.setStatus(201);
        return "users/addedUser";
    }
}
