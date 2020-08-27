package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class HomeController {

    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;

    @Autowired
    public HomeController(NoteService noteService, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping(value = {"/", "/dashboard"})
    public ModelAndView showIndex(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        ModelAndView modelAndView = new ModelAndView("home");

        modelAndView.addObject("notes", noteService.getAllByUserId(user.getUserId()));
        modelAndView.addObject("files", fileService.getAllByUserId(user.getUserId()));
        modelAndView.addObject("credentials", credentialService.getAllByUserId(user.getUserId()));

        return modelAndView;
    }
}
