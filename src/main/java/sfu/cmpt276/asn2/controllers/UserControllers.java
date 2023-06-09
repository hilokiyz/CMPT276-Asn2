package sfu.cmpt276.asn2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    //get user
    @GetMapping("/users/edit/{uid}")
    public String getUser(Model model, @PathVariable String uid, HttpServletResponse response) {
        System.out.println("Getting user " + uid);

        int id = Integer.parseInt(uid);

        User u = userRepo.findById(id).get();

        // update user u to database
        // userRepo.save(u);

        model.addAttribute("user", u);
        return "users/editUser";
    }

    @PostMapping("users/editUser/{uid}")
    public String editUser(@RequestParam Map<String, String> editedUser, Model model, @PathVariable String uid,
            HttpServletResponse response) {
        System.out.println("Editing user");

        int id = Integer.parseInt(uid);
        User u = userRepo.findById(id).get();

        // set new inputs to that user
        u.setName(editedUser.get("name"));
        u.setWeight(Integer.parseInt(editedUser.get("weight")));
        u.setHeight(Integer.parseInt(editedUser.get("height")));
        u.setHairColor(editedUser.get("hairColor"));
        u.setGPA(Double.parseDouble(editedUser.get("GPA")));

        userRepo.save(u);

        response.setStatus(201);
        return "users/editedUser";
    }

    // delete a user
    @GetMapping("/users/delete/{uid}")
    public String deleteUser(Model model, @PathVariable String uid, HttpServletResponse response) {
        System.out.println("Getting user " + uid);

        int id = Integer.parseInt(uid);

        User u = userRepo.findById(id).get();
        userRepo.delete(u);

        // model.addAttribute("user");
        response.setStatus(201);
        return "users/deletedUser";
    }

    // add a new user
    @PostMapping("/users/addUser")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        System.out.println("Added user");
        String newName = newuser.get("name");
        int newWeight = Integer.parseInt(newuser.get("weight"));
        int newHeight = Integer.parseInt(newuser.get("height"));
        String newHairColor = newuser.get("hairColor");
        double newGPA = Double.parseDouble(newuser.get("GPA"));
        userRepo.save(new User(newName, newWeight, newHeight, newHairColor, newGPA));

        response.setStatus(201);
        return "users/addedUser";
    }
}
