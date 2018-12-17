package com.daicq.web.rest;

import com.daicq.service.UserService;
import com.daicq.service.UsersService;
import com.daicq.service.dto.UserDTO;
import com.daicq.service.dto.UsersDTO;
import com.daicq.service.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    UsersService usersService;

    @RequestMapping(value = {"/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "/login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // (1) (en)
        // After user login successfully.
        // (vi)
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

    // private final Logger log = LoggerFactory.getLogger(UserResource.class);
    // show trang đăng ký
    @RequestMapping(value = "/registered")
    public String showRegistered(Model model){
        UsersDTO usersDTO = new UsersDTO();
        model.addAttribute("customer", usersDTO);
        return "/registered";
    }

    // thêm category
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute("customer") UsersDTO usersDTO, ModelMap model) {
        //   log.debug("request to create user : {}", userDTO);
        // customer.setEnabled(true);
        UsersDTO usersDTO1 = usersService.save(usersDTO);
        if (null != usersDTO1) {
            model.addAttribute("message", "Update success");
            model.addAttribute("customer",usersDTO1);
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("customer", usersDTO1);
        }
        return "redirect:/login";
    }
}
