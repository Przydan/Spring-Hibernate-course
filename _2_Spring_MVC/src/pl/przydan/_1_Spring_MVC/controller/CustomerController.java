package pl.przydan._1_Spring_MVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.przydan._1_Spring_MVC.model.Customer;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping("/showForm")
    public String showForm(Model model) {
        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @RequestMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult
    ) {
        if (theBindingResult.hasErrors()) {
            return "customer-form";
        } else {
            return "customer-page";
        }
    }

}