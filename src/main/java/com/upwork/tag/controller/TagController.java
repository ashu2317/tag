package com.upwork.tag.controller;

import com.upwork.tag.model.TagRequest;
import com.upwork.tag.model.TagResponse;
import com.upwork.tag.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/tags")
public class TagController {

    private TagService tagService;

    @GetMapping(value = {"", "/{id}"})
    public List<TagResponse> listTag(@PathVariable(required = false) String id) {
      return tagService.listTag(id);
    }

    @PostMapping
    public ResponseEntity addTag(@RequestBody TagRequest tagRequest) {
       TagResponse tag =  tagService.addTag(tagRequest);
        return ResponseEntity.created(URI.create("/api/tags/"+ tag.getId())).body(tag);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok("Tag Deleted");
    }

    @PutMapping
    public ResponseEntity updateTag(@RequestBody TagRequest tagRequest) {
        TagResponse tag =  tagService.updateTag(tagRequest);
        return ResponseEntity.ok("Tag Updated");
    }
}
