package com.jang.Mishop.vo.Skill_product;


import lombok.Data;

import java.io.Serializable;

@Data
public class GetskillProductRes implements Serializable {

    private String productName;

    private Double productPrice;

    private String productPicture;

    private Long startTime;

    private Long endTime;
}
