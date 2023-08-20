package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import static com.commandoby.sonyShop.enums.PagesPathEnum.REGISTER_PAGE;
import static com.commandoby.sonyShop.enums.RequestParamEnum.EMAIL;

@Controller
@RequestMapping("/sonyshop")
@SessionAttributes({"user", "order"})
public class SignInController {

    //private final Logger log = LogManager.getLogger(UserController.class);
    private final UserService userService;

    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public ModelAndView signIn(SessionStatus sessionStatus//, 
                               //@ModelAttribute("user") User user
    		) throws ControllerException {
        //log.info("User " + user.getEmail() + " left the store.");
        sessionStatus.setComplete();

        return new ModelAndView(PagesPathEnum.SIGN_IN_PAGE.getPath(), new ModelMap());
    }

    @PostMapping("/signin")
    public ModelAndView register(@RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String date_of_birth,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String second_password) throws ControllerException {
        ModelAndView modelAndView = new ModelAndView();

        try {
            modelAndView = userService.register(name, surname, date_of_birth, email, password, second_password);
        } catch (ServiceException e) {
            //log.error(e);
        }


        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView newUser(@RequestParam(required = false) String email) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute(EMAIL.getValue(), email);

        return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
    }
}
