package com.valdemarjr.batchexample.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Coffee {

  @Id
  @SequenceGenerator(name = "coffee_generator", sequenceName = "coffee_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coffee_generator")
  private Long id;

  private String reviewDate;
  private String handle;
  private Double rating;
  private String helpfulnessRating;

  @Lob
  private String review;

  public Coffee(
      String reviewDate, String handle, Double rating, String helpfulnessRating, String review) {
    this.reviewDate = reviewDate;
    this.handle = handle;
    this.rating = rating;
    this.helpfulnessRating = helpfulnessRating;
    this.review = review;
  }
}
