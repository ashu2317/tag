package com.upwork.tag.service;

import com.upwork.tag.model.TagRequest;
import com.upwork.tag.model.TagResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TagService {
    List<TagResponse> listTag(String id);
    TagResponse addTag(TagRequest tagRequest);
    void deleteTag(String id);
    TagResponse updateTag(TagRequest tagRequest);
}
