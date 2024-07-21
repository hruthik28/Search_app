package com.searchapp.jobListing.repository;

import com.searchapp.jobListing.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobPostRepository extends MongoRepository<JobPost, String> {

}
