package com.squiky.movieservice.model.rating;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {
    private long userId;
    private int rating;
}
