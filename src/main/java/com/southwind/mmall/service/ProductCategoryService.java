package com.southwind.mmall.service;

import com.southwind.mmall.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.mmall.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 建强
 * @since 2021-09-11
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVO> getAllProductCategoryVo();

}
