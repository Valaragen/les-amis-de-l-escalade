package com.rudy.ladl.service;

import com.rudy.ladl.core.site.AddedTag;
import com.rudy.ladl.core.site.Site;
import com.rudy.ladl.core.site.Tag;
import com.rudy.ladl.core.site.embeddable.AddedTagId;
import com.rudy.ladl.core.user.User;
import com.rudy.ladl.repository.AddedTagRepository;
import com.rudy.ladl.repository.TagRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TagService {
    private TagRepository tagRepository;
    private AddedTagRepository addedTagRepository;

    public TagService(TagRepository tagRepository, AddedTagRepository addedTagRepository) {
        this.tagRepository = tagRepository;
        this.addedTagRepository = addedTagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public void addOfficialTagOnSite(Site site, User user) {
        Tag tag = tagRepository.findById(1L).orElse(null);

        AddedTagId addedTagId = new AddedTagId(site, tag);
        AddedTag addedTag = new AddedTag();
        addedTag.setId(addedTagId);
        addedTag.setUser(user);

        addedTagRepository.save(addedTag);
    }

    public void removeOfficialTagOnSite(Site site) {
        Tag tag = tagRepository.findById(1L).orElse(null);

        AddedTag addedTag = addedTagRepository.findByIdSiteAndIdTag(site, tag).orElse(null);
        addedTagRepository.delete(addedTag);

    }

}
