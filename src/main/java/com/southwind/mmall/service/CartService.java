package com.southwind.mmall.service;

import com.southwind.mmall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.mmall.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 建强
 * @since 2021-09-11
 */
public interface CartService extends IService<Cart> {
    public List<CartVO> findAllCartVOByUserId(Integer id);
}
