package pl.przydan.security_web_app_db.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.przydan.security_web_app_db.entity.User;
import pl.przydan.security_web_app_db.service.UserService;
import pl.przydan.security_web_app_db.user.CrmUser;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("crmUser", new CrmUser());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
            BindingResult theBindingResult,
            Model theModel) {

        String userName = theCrmUser.getUserName();
        logger.info("==-->Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("==-->User name already exists.");
            return "registration-form";
        }
        // create user account
        userService.save(theCrmUser);

        logger.info("==-->Successfully created user: " + userName);

        return "registration-confirmation";
    }
}
