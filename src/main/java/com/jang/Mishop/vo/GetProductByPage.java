package com.jang.Mishop.vo;


import lombok.Data;

@Data
public class GetProductByPage {
    private  int catgoryId;
    private int CurentId;
    private  int PageSize;
}
