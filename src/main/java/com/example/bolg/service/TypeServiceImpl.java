package com.example.bolg.service;

import com.example.bolg.dao.TypeDao;
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

import java.util.List;

@Service
public class TypeServiceImpl  implements  TypeService{

@Autowired
private TypeDao typeDao;
@Transactional
    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.findOne(id);
    }

    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }


    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
    Type t =typeDao.findOne(id);
    if(t==null){
        throw  new NotFoundException("不存在");
    }
        BeanUtils.copyProperties(type,t);

        return typeDao.save(t);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.delete(id);
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
    Sort sort =new Sort(Sort.Direction.DESC,"blogs.size");
    Pageable pageable=new PageRequest(0,size,sort);
        return typeDao.findTop(pageable);
    }
}
