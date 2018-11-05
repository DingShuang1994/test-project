package com.douniwan.controller;

import com.douniwan.domain.Order;
import com.douniwan.domain.Product;
import com.douniwan.service.IOrderService;
import com.douniwan.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    /**
     * 查询所有产品信息 为分页
     * @return
     * @throws Exception
     */
//    @RequestMapping("/findAll.do") //与前端控制器相对应
//    public ModelAndView findAll() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        List<Order> orders = orderService.findAll();
//        System.out.println(orders);
//        mv.addObject("ordersList",orders);
//        mv.setViewName("orders-list");
//        return mv;
//    }
    @RequestMapping("/findAll.do") //与前端控制器相对应 这里不写.do请求写了.do 系统会自动添加上.do
    @RolesAllowed("USER") //jsr250使用注解控制权限时用roleAllowed 且只需角色名
//    @Secured("ROLE_USER")  //security自带权限注解控制就要写全了放行的条件
    public ModelAndView findAll(@RequestParam(name="page",required=true,defaultValue ="1") int page ,
                                @RequestParam(name="size",required=true,defaultValue ="4") int size ) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Order> orders = orderService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id",required=true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Order order = orderService.findById(id);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }


}
