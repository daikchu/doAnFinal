package com.daicq.web.rest;

import com.daicq.domain.Book;
import com.daicq.service.BookService;
import com.daicq.service.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController{
    @Autowired
    BookService bookService;
    private static final int dataPerPage = 6; // moi trang co 7 data
    private static int totalPages = 0;

    @RequestMapping(method = RequestMethod.GET)
    public String showProduct(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (s != null) {
            SecurityContextImpl context = (SecurityContextImpl) s;
            String loggedInUser = context.getAuthentication().getName();
            model.addAttribute("userName", loggedInUser);
        }
        return "home";
    }

    /// list product ở trang chủ
    @ModelAttribute("listproduct6")
    public List<BookDTO> listproduct6(Model model) {
        List<BookDTO> productList = bookService.findAll();

        return productList;
    }

/*    @ModelAttribute("producttop")
    public String reporttop(Model model) {
        CTHD orderDetail = new OrderDetail();
        model.addAttribute("orderDetail", orderDetail);
        List<Object[]> listTest1 =  orderDetailRepository.topdathangnhieu();
        System.out.println("id = " + listTest1.get(0)[0] + "productId = " + listTest1.get(0)[1]);
        model.addAttribute("listTest1",listTest1);
        return "producttop";
    }*/

    @RequestMapping(value = "detail")
    public String showProductDetail(
        @RequestParam("id") String id, Model model) {
        BookDTO product = bookService.findById(id);
        model.addAttribute("product", product);
        return "detail";
    }

    //product by category
    @RequestMapping(value = "productbycategory")
    public String showproductbyid(Model model, @RequestParam("categoryId") String categoryId) {
        List<BookDTO> productList = bookService.findByDanhMucId(categoryId);
        model.addAttribute("productList", productList);
        return "product";
    }

    // product by supline
    @RequestMapping(value = "productbysupplier")
    public String showproductbysup(Model model, @RequestParam("supplierId") String supplierId) {
        List<BookDTO> productList = bookService.findByNhaXB(supplierId);
        model.addAttribute("productList", productList);
        return "product";
    }
}
