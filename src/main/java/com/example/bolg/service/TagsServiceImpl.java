package com.example.bolg.service;

import com.example.bolg.dao.TagsDao;
import com.example.bolg.dao.TypeDao;
import com.example.bolg.po.Tag;
import com.example.bolg.po.Type;
import com.example.bolg.web.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagsServiceImpl implements  TagsService{

    @Autowired
    private TagsDao tagsDao;
    @Transactional

    @Override
    public Tag saveTag(Tag tag) {
        return tagsDao.save(tag);

    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagsDao.findOne(id);
    }

    @Override
    public List<Tag> listTag() {
        return tagsDao.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagsDao.findAll(covertTolist(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort=new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable =new PageRequest(0,size,sort);
        return tagsDao.findTop(pageable);
    }

    private  List<Long> covertTolist(String ids){
        List<Long> list =new ArrayList<>();
        if(!"".equals(ids)&&ids!=null){
            String [] idarray=ids.split(",");
            for(int i=0;i<idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;

    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagsDao.findByName(name);
    }
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagsDao.findAll(pageable);
    }
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t =tagsDao.findOne(id);
        if(t==null){
            throw  new NotFoundException("不存在");
        }
        BeanUtils.copyProperties(tag,t);

        return tagsDao.save(t);
    }
    @Transactional
    @Override
    public void deleteTag(Long id) {
 tagsDao.delete(id);
    }
}
