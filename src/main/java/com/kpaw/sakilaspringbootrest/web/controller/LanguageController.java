package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.Language;
import com.kpaw.sakilaspringbootrest.service.LanguageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LanguageController {

    private LanguageService languageService;

    public LanguageController(LanguageService languageService){
        this.languageService = languageService;
    }

    @GetMapping("/languages")
    public List<Language> findAll(){
        return languageService.findAll();
    }

    @GetMapping("/languages/{langId}")
    public Language findById(@PathVariable byte langId){
        return languageService.findById(langId);
    }

    @PostMapping("/languages")
    public String saveNewLanguage(@RequestBody Language language){
        language.setLanguageId((byte) 0);
        languageService.save(language);
        return "language saved: " + language;
    }

    @PutMapping("/languages")
    public String updateLanguage(@RequestBody Language language){
        languageService.save(language);
        return "language updated: " + language;
    }

    @DeleteMapping("/languages/{langId}")
    public String deleteLanguageById(@PathVariable byte langId){
        languageService.deleteById(langId);
        return "language with id " + langId + " deleted";
    }
}
