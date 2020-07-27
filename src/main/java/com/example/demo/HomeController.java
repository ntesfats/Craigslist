package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/processUser")
    public String processUser(@Valid User user, BindingResult result, Model model){
        System.out.println("Somethingggggg");
        User anotherUser = userRepository.findByUserName(user.getUserName());

        Boolean userNameIsNotUniq = anotherUser != null;
        if(result.hasErrors() && userNameIsNotUniq) {

            return "addUser";
        }
        userRepository.save(user);
        Long id = user.getId();

        User user_t = userRepository.findById(id).get();
        model.addAttribute("user", user_t);
        return "userProfile";
    }

//    @GetMapping("/userProfile")
//    public String userProfile(Model model) {
//
//        User user = userRepository.findById(iddf).get();
//        model.addAttribute("user", user);
//        return "userProfile";
//    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());

        return "addUser";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable long id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/";
    }

//    Login page

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/processLogin")
    public String processLogin(@RequestParam("userName") String userName,
                               @RequestParam("password") String password, Model model) {
        User user = userRepository.findByUserNameAndPassword(userName, password);

        if (user == null) {
            return "redirect:/login";
        }

        Long id = user.getId();
        User user_t = userRepository.findById(id).get();
        model.addAttribute("user", user_t);
        return "userProfile";
    }

















//    Job

    @GetMapping("/addJob")
    public String addJob(Model model) {
        model.addAttribute("job", new Job());
//        model.addAttribute("authorId");
        return "addJob";
    }
    @PostMapping("/processJob")
    public String processJob(@Valid Job job, BindingResult result, Model model){
        if(result.hasErrors()) {
            return "addJob";
        }
        jobRepository.save(job);
        return "redirect:/";
    }


    @GetMapping("/jobDetails/{id}")
    public String jobDetails(@PathVariable long id, Model model) {
        Job job = jobRepository.findById(id).get();
        model.addAttribute("job", job);
        return "jobDetails";
    }

    @GetMapping("/updateJob/{id}")
    public String updateJob(@PathVariable long id, Model model) {
        model.addAttribute("job", jobRepository.findById(id).get());

        return "addUser";
    }

    @GetMapping("/deleteJob/{id}")
    public String deleteJob(@PathVariable long id, Model model) {
        jobRepository.deleteById(id);
        return "redirect:/";
    }




}
