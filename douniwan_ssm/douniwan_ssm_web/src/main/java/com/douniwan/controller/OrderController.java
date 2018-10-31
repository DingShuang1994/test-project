package com.douniwan.controller;

import com.douniwan.domain.Order;
import com.douniwan.domain.Product;
import com.douniwan.service.IOrderService;
import com.douniwan.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping("/findAll.do") //与前端控制器相对应
    public ModelAndView findAll(@RequestParam(name="page",required=true,defaultValue ="1") int page ,
                                @RequestParam(name="size",required=true,defaultValue ="4") int size ) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Order> orders = orderService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

}
