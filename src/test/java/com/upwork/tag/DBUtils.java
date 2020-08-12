package com.upwork.tag;

import com.upwork.tag.entity.Tag;
import com.upwork.tag.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DBUtils {
    private TagRepository tagRepository;

    public Tag insertTagInDB(Tag tag) {
        return tagRepository.save(tag);
    }

}
