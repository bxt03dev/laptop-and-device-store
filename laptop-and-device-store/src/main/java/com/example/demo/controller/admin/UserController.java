package com.example.demo.controller.admin;

import com.example.demo.domain.User;
import com.example.demo.service.UploadService;
import com.example.demo.service.UserService;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController{

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

   public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
       this.userService = userService;
       this.uploadService = uploadService;
       this.passwordEncoder = passwordEncoder;
   }

   @RequestMapping("/")
   public String getHomePage(Model model){
       List<User> arrUsers = this.userService.getAllUsersByEmail("chilloutmusic03@gmail.com");
       System.out.println(arrUsers);
       model.addAttribute("bxt", "test");
       model.addAttribute("bxtsama", "from controller with model");
       return "hello";
   }

   @RequestMapping("admin/user")
   public String getUserPage(Model model){
       List<User> users = this.userService.getAllUser();
       model.addAttribute("users1", users);
       return "admin/user/show";
   }

   @GetMapping("/admin/user/create")
   public String getResgisterPage(Model model){ //Model là 1 interface trong MVC để truyền dữ liệu giữa Controller và View
       model.addAttribute("newUser", new User());
       return "admin/user/create";
   }

   @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model,
                                 @ModelAttribute("newUser") @Valid User bxt,
                                 BindingResult newUserBindingResult,
                                 @RequestParam("bxtFile") MultipartFile file
                                 ){

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for(FieldError error : errors){
            System.out.println(error.getField() + " " + error.getDefaultMessage());
        }

        if(newUserBindingResult.hasErrors()){
            return "admin/user/create";
        }

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(bxt.getPassword());

        bxt.setAvatar(avatar);
        bxt.setPassword(hashPassword);
        bxt.setRole(this.userService.getRoleByName(bxt.getRole().getName()));
        this.userService.handleSaveUser(bxt);

        return "redirect:/admin/user";
   }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id){
       User user = this.userService.getUserById(id);
       model.addAttribute("user", user);
       model.addAttribute("id", id);
       return "/admin/user/detail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id){
       User user = this.userService.getUserById(id);
       model.addAttribute("newUser", user);
       model.addAttribute("id", id);
       return "/admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User bxt){
        User currentUser = this.userService.getUserById(bxt.getId());
        if(currentUser != null){
            currentUser.setFullName(bxt.getFullName());
            currentUser.setAddress(bxt.getAddress());
            currentUser.setPhone(bxt.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id){
       model.addAttribute("id", id);
       model.addAttribute("newUser", new User());
       return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User bxt){
        this.userService.deleteUserById(bxt.getId());
        return "redirect:/admin/user";
    }


}

