package com.Spring.Learning.e_comproj.controller;


import com.Spring.Learning.e_comproj.model.Products;
import com.Spring.Learning.e_comproj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@CrossOrigin //what it does is allows the data to parse from one area or host to the other
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;



    @GetMapping("/products")
    public ResponseEntity<List<Products>> getallproducts(){
        return new ResponseEntity<>(service.getAllproducts(), HttpStatus.OK);
    }
    //here response status is used to define the nature of the https and other stuffs


    @GetMapping("/products/{pid}")
    public  ResponseEntity <Products> getbypid(@PathVariable int pid){
        Products prod= service.getbypid(pid);
        if(prod!=null) {
            return new ResponseEntity<>(prod, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<?> addprod(@RequestPart Products p,@RequestPart MultipartFile imagefile ){
       try{
           Products p1=service.addprod(p,imagefile);
            return new ResponseEntity<>(p1,HttpStatus.CREATED);

       }
       catch(Exception e){
           return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

       }
    }

    @GetMapping("/products/{pid}/image")
    public ResponseEntity<byte[]> getimagebyprodid(@PathVariable int pid){
        Products prod=service.getbypid(pid);
        byte[] imagefile=prod.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(prod.getImageType()))
                .body(imagefile);


    }

    @PutMapping("/products/{pid}")
    public ResponseEntity<String> updateproduct(@PathVariable int pid,@RequestPart Products p,@RequestPart MultipartFile imagefile) throws IOException {
        Products p1= null;
        try {
            p1 = service.updateproduct(pid,p,imagefile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(p1!=null){
            return  new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("failed to update",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{pid}")
    public ResponseEntity<String> deletproduct(int pid){
        Products p1=service.getbypid(pid);
        if(p1!=null){
            service.deleteproduct(pid);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
        }
    }
        @GetMapping("/products/search")
        public ResponseEntity<List<Products>> searchbykey( @RequestParam String keyword){
            List<Products> prod1=service.searchbykey(keyword);
            return new ResponseEntity<>(prod1,HttpStatus.OK);

        }



}
