package com.searchapp.jobListing.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.searchapp.jobListing.model.JobPost;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Component
public class SearchRepositoryImpl implements SearchRepository{

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter converter;

    @Override
    public List<JobPost> findByText(String text) {

        final List<JobPost> posts = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("jobListing");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                                    new Document("text",
                                                    new Document("query", text) // search based on text input
                                                    .append("path", Arrays.asList("techs", "desc", "profile")))), //specifies the fields to be searched
                                                    new Document("$sort",
                                                    new Document("exp", 1L)), //this is in ascending order
                                                    new Document("$limit", 5L))); // max of 5 dcouments will be retrived

        // doc and posts are different types, posts is a java class, and doc is a document, to add document to java class MongoConverter is used
        result.forEach(doc -> posts.add(converter.read(JobPost.class, doc)));
        return posts;
    }
}
