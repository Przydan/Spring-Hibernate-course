package pl.przdyan.spring_crm_rest_client.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.przdyan.spring_crm_rest_client.entity.Customer;
import pl.przdyan.spring_crm_rest_client.service.CustomerServiceClientImpl;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerServiceClientImpl customerService;

    public CustomerController(CustomerServiceClientImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getCustomers());
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.saveOrUpdate(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormUpdatePage(@RequestParam("customerId") Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomer(id));
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") Long id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }
}