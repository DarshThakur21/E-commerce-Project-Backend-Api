package com.Spring.Learning.e_comproj.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
//import java.sql.Date;
import java.util.*;
import java.util.Date;

@Entity
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
public class Products {
     @Id
     @GeneratedValue( strategy  = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
    private Date releasedate;
    private boolean available;
    private int quantity;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;


//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }
//
//    public void setImageType(String imageType) {
//        this.imageType = imageType;
//    }
//
//    public void setImageDate(byte[] imageDate) {
//        this.imageDate = imageDate;
//    }
}
