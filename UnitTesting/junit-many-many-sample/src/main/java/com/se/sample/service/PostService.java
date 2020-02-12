package com.se.sample.service;

import com.se.sample.entity.Post;
import com.se.sample.model.PostModel;

public interface PostService {

    Post create(PostModel postModel);
}
