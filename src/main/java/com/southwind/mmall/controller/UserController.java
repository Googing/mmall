package com.southwind.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.mmall.entity.User;
import com.southwind.mmall.enums.GenderEnum;
import com.southwind.mmall.service.CartService;
import com.southwind.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 建强
 * @since 2021-09-11
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @PostMapping("/register")
    public String register(User user, Model model){
        System.out.println(user.getGender()+"==="+user.getGenderCode());
        boolean result = false;
        try {
            if(user.getGenderCode() == 1){
                user.setGender(GenderEnum.MAN);
            }
            if(user.getGenderCode() == 0){
                user.setGender(GenderEnum.WOMAN);
            }
            result = userService.save(user);
        } catch (Exception e) {
            model.addAttribute("error",user.getLoginName()+"已存在！请重新输入！");
            return "register";
        }
        if(result) return "login";
        return "register";
    }
    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name",loginName);
        wrapper.eq("password",password);
        User user = userService.getOne(wrapper);
        if (user == null){
            return "login";
        }else {
            session.setAttribute("user",user);
            return "redirect:/productCategory/list";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    /**
     * 查询用户信息
     */
    @GetMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)session.getAttribute("user");
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

