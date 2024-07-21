package com.searchapp.jobListing.repository;

import com.searchapp.jobListing.model.JobPost;

import java.util.List;

public interface SearchRepository {

    List<JobPost> findByText(String text);
}
