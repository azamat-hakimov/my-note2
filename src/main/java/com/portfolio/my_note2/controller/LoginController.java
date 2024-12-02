package com.portfolio.my_note2.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/checkingLogin")
    public String postLogin(@RequestParam("password") String inputPassword, Model model) {
        
        String filePath = "D:\\java-learning\\my-projects\\for-portfolio\\basics\\MyNotes2\\db\\password.txt";


        String message;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String password = reader.readLine();
            reader.close();
            if (password != null){
                if (inputPassword.equals(password)){
                    return "redirect:/notes";
                }else {
                    message = "There is no password that matches!";
                    model.addAttribute("message", message);
                }
            }else {
                return "redirect:/signup";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "login";
    }


    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }


    @PostMapping("/checkingSignup")
    public String postSignup(@RequestParam("newPassword")String newPassword, @RequestParam("confirmPassword")String confirmPassword, Model model){
        String filePath4Password = "D:\\java-learning\\my-projects\\for-portfolio\\basics\\MyNotes2\\db\\password.txt";

        String message;

        if (newPassword.equals(confirmPassword)){

            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath4Password));
    
                String password = reader.readLine();
                reader.close();
    
                if (password == null){
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath4Password));
        
                        writer.write(confirmPassword);
                        writer.close();

                        return "redirect:/notes";
        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        
                }else {
                    return "redirect:/login";
                }
    
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            message = "Password don't match!";
            model.addAttribute("message", message);
            return "signup";
        }

        return "signup";
        
    }
    
    

}
