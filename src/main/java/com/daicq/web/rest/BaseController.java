package com.daicq.web.rest;

import com.daicq.repository.DanhMucRepository;
import com.daicq.service.DanhMucService;
import com.daicq.service.dto.DanhMucDTO;
import com.daicq.service.mapper.DanhMucMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class BaseController {
    @Autowired
    DanhMucRepository danhMucRepository;
    @Autowired
    DanhMucMapper danhMucMapper;
    @Autowired
    DanhMucService danhMucService;

/*    @Autowired
    SuppliersRepository suppliersRepository;*/

    @ModelAttribute("categoryList")
    public List<DanhMucDTO> showCategory(Model model) {
        List<DanhMucDTO> categoryList = danhMucService.findAll();
              //  (List<DanhMucDTO>) danhMucRepository.findAll();

        return categoryList;
    }

   /* @ModelAttribute("supplierList")
    public List<Supplier> supplierList1(Model model) {
        List<Supplier> supplierList =
                (List<Supplier>) suppliersRepository.findAll();
        return supplierList;
    }*/




}
