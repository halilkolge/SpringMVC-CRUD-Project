package com.mvc.controller;

import com.mvc.dto.UserDto;
import com.mvc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UsersService usersService;

//    @GetMapping(value = "/")
//    public ModelAndView homePage(Model model){
//        ModelAndView mav = new ModelAndView("TableUser");
//        model.addAttribute("user", usersService.list());
//        return mav;
//    }

    @GetMapping("/createUser")
    public ModelAndView createUserView(Model model){
        ModelAndView mav = new ModelAndView("CreateUser");
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return mav;
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public RedirectView create(UserDto userDto) {
        if (userDto.id != 0){
            UserDto deleteUser = usersService.findById(userDto.id);
            usersService.list().remove(deleteUser);
            userDto.setId(userDto.getId());
            usersService.update(userDto);
        }else {
            usersService.save(userDto);
        }

        return new RedirectView("/list");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView userList(Model model){
        ModelAndView mav = new ModelAndView("TableUser");
        model.addAttribute("user", usersService.list());
        return mav;
    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public ModelAndView update(Model model, int id){
        ModelAndView mav = new ModelAndView("UpdateUser");
        model.addAttribute("user", usersService.findById(id));
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(Model model, int id){
        UserDto deleteUser = usersService.findById(id);
        usersService.list().remove(deleteUser);
        ModelAndView mav = new ModelAndView("TableUser");
        model.addAttribute("user", usersService.list());
        return mav;
    }


}
