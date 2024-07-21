package com.searchapp.jobListing.controller;

import javax.servlet.http.HttpServletResponse;

import com.searchapp.jobListing.model.JobPost;
import com.searchapp.jobListing.repository.JobPostRepository;
import com.searchapp.jobListing.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // this is the port of react application and only this is required for connection react to spring
public class JobListingController {

    @Autowired
    JobPostRepository repo;

    @Autowired
    SearchRepository searchRepo;

    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/jobPosts")
    public List<JobPost> getAllJobPosts() {
        return repo.findAll();
    }

    @PostMapping("/postJob")
    public JobPost addJobPost(@RequestBody JobPost jobPost) {
        return repo.save(jobPost);
    }

    @GetMapping("/search/{text}")
    public List<JobPost> search(@PathVariable String text) {
        return searchRepo.findByText(text);
    }
}
