package com.valdemarjr.batchexample.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Coffee {

//  @Id private Long id;

  private String reviewDate;
  private String handle;
  private Double rating;
  private String helpfulnessRating;
  private String review;
}
