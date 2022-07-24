package com.example.learningresourcesapivanshika_jain.controllers;

import com.example.learningresourcesapivanshika_jain.entity.LearningResource;
import com.example.learningresourcesapivanshika_jain.service.LearningResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class LearningResourceController {
    private final LearningResourceService lrs;


    public LearningResourceController(LearningResourceService lrs) {
        this.lrs = lrs;
    }

    @GetMapping("/Resources")
    public List<LearningResource> getResources() {
        return lrs.getLearningResources();
    }

    @PostMapping("/Resources")
    public void putResources(@RequestBody List<LearningResource> lr) {

        lrs.saveLearningResources(lr);

    }

    @DeleteMapping("/Resources/{id}")
    public void deleteResource(@PathVariable int id) {
        lrs.deleteLearningResource(id);
    }
}
