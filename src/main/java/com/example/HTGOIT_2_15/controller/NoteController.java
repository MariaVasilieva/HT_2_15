package com.example.HTGOIT_2_15.controller;

import com.example.HTGOIT_2_15.model.Note;
import com.example.HTGOIT_2_15.model.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public String getAllNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note-list";
    }
    @PostMapping("/delete")
    public String deleteNote(@RequestParam long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
    @GetMapping("/edit")
    public String editNote(@RequestParam long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note-edit";
    }
    @PostMapping("/edit")
    public String editNote(@ModelAttribute Note note) {
        noteService.update(note);
        return "redirect:/note/list";
    }
}
