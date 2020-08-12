package com.upwork.tag.service.impl;

import com.upwork.tag.entity.Tag;
import com.upwork.tag.model.TagRequest;
import com.upwork.tag.model.TagResponse;
import com.upwork.tag.repository.TagRepository;
import com.upwork.tag.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;


    @Override
    public List<TagResponse> listTag(String id) {
        List<TagResponse> responses = new ArrayList<>();
        if (id == null) {
            List<Tag> tags = tagRepository.findAll();
            responses =  tags.stream().map(tag -> {
                TagResponse response = new TagResponse();
                BeanUtils.copyProperties(tag, response);
                return response;
            }).collect(Collectors.toList());
        } else {
            Tag tag = tagRepository.findById(id).get();
            TagResponse response = new TagResponse();
            BeanUtils.copyProperties(tag, response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public TagResponse addTag(TagRequest tagRequest) {
        Tag tag = new Tag();
        TagResponse response  = new TagResponse();
        BeanUtils.copyProperties(tagRequest, tag);
        tag = tagRepository.save(tag);
        BeanUtils.copyProperties(tag, response);
        return response;
    }

    @Override
    public void deleteTag(String id) {
        tagRepository.deleteById(id);
    }

    @Override
    public TagResponse updateTag(TagRequest tagRequest) {
        Tag tag = new Tag();
        TagResponse response  = new TagResponse();
        BeanUtils.copyProperties(tagRequest, tag);
        tag = tagRepository.save(tag);
        BeanUtils.copyProperties(tag, response);
        return response;
    }
}
