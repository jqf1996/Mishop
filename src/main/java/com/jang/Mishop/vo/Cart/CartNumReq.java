package com.jang.Mishop.vo.Cart;


import lombok.Data;

@Data
public class CartNumReq {

    private int user_id;
    private int product_id;
    private int product_num;
}
