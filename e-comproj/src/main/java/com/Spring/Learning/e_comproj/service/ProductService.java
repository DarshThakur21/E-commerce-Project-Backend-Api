package com.Spring.Learning.e_comproj.service;


import com.Spring.Learning.e_comproj.model.Products;
import com.Spring.Learning.e_comproj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo p_repo;


    public List<Products> getAllproducts() {
        return p_repo.findAll();

    }

    public Products getbypid(int pid) {
        return p_repo.findById(pid).orElse(new Products());
    }


    public Products addprod(Products p, MultipartFile imageFile) throws IOException {
        p.setImageName(imageFile.getOriginalFilename());
        p.setImageType(imageFile.getContentType());
        p.setImageData(imageFile.getBytes());
    return p_repo.save(p);
    }


    public Products updateproduct(int id, Products p, MultipartFile imageFile) throws IOException {
        p.setImageName(imageFile.getOriginalFilename());
        p.setImageType(imageFile.getContentType());
        p.setImageData(imageFile.getBytes());
        return p_repo.save(p);
    }

    public void  deleteproduct(int pid) {

          p_repo.deleteById(pid);

    }

    public List<Products> searchbykey(String keyword) {
        return p_repo.searchkey(keyword);
    }
}
