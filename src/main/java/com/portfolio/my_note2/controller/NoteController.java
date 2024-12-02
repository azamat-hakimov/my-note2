package com.portfolio.my_note2.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class NoteController {
    

    @GetMapping()
    public String getHome(){
        return "home";
    }

    @GetMapping("/notes")
    public String getNotes(Model model){

        String filePath = "D:\\java-learning\\my-projects\\for-portfolio\\basics\\MyNotes2\\db\\notes.txt";

        List<String> notes = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }

            reader.close();
            if (notes == null || notes.isEmpty()) {
                model.addAttribute("ifNull", "No notes available.");
            } else {
                model.addAttribute("notes", notes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "notes";
    }

    @GetMapping("/write")
    public String getWrite(){
        return "write";
    }

    @PostMapping("/postingNote")
    public String postNote(@RequestParam("note")String note){

        String filePath4Note = "D:\\java-learning\\my-projects\\for-portfolio\\basics\\MyNotes2\\db\\notes.txt";

        try {
            BufferedWriter writerNote = new BufferedWriter(new FileWriter(filePath4Note, true));
            writerNote.write(note);
            writerNote.newLine();
            writerNote.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/notes";


    }

}
