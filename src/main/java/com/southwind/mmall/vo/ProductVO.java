package com.southwind.mmall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 在每个类别模块显示相关商品信息
 */
@Data
@AllArgsConstructor
public class ProductVO {

    private Integer id;
    private String name;
    private Float price;
    private String fileName;

}
