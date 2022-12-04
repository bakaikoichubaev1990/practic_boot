package com.example.practic_boot.api;

import com.example.practic_boot.model.Company;
import com.example.practic_boot.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;

    }

    @GetMapping("/getAllCompanies")
    public String getCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "/company/allCompanies";

    }
    @GetMapping("/addCompany")
    public String addCompany(Model model){
        model.addAttribute("company", new Company());
        return "/company/addCompany";
    }
    @PostMapping("/saveCompany")
    public String setCompany(@ModelAttribute("company") Company company){
        companyService.addCompany(company);
        return "redirect:/getAllCompanies";
    }
    @GetMapping("updateCompany")
    public String updateCompany(@RequestParam("companyId") Long id, Model model){
        Company company  = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "/company/updateCompany";
    }
    @PostMapping("/updateCompany")
    public String saveUpdateCompany(@ModelAttribute("company") Company company){
        companyService.updateCompany(company);
        return "redirect:/getAllCompanies";
    }
    @RequestMapping("/deleteCompany")
    public String deleteCompany(@RequestParam ("companyId") Long id){
        companyService.deleteCompany(id);
        return "redirect:/getAllCompanies";
    }
}
