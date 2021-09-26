package com.southwind.mmall.service;

import com.southwind.mmall.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.mmall.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 建强
 * @since 2021-09-11
 */
public interface OrderService extends IService<Orders> {
   public boolean save(Orders orders, User user,String address, String remark);
}
