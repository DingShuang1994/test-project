package com.douniwan.controller;

import com.douniwan.domain.Product;
import com.douniwan.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * 查询所有产品信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do") //与前端控制器相对应
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList",products);
        mv.setViewName("product-list1");
        return mv;
    }

    /**
     * 保存产品信息
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception { //获取表单数据
        productService.save(product);
//        response.sendRedirect("/product/findAll.do"); //
        return "redirect:findAll.do";
    }
}
