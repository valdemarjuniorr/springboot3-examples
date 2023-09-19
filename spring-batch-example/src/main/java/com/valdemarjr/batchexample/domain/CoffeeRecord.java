package com.valdemarjr.batchexample.domain;

public record CoffeeRecord(
    String reviewDate, String handle, Double rating, String helpfulnessRating, String review) {}
