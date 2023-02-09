package com.example.restaurantmanagementjavaspringboot.controller;
import com.example.restaurantmanagementjavaspringboot.dto.AccountDto;
import com.example.restaurantmanagementjavaspringboot.dto.AdminEditAccountDto;
import com.example.restaurantmanagementjavaspringboot.dto.AdminViewAccountDto;
import com.example.restaurantmanagementjavaspringboot.service.AdminService;
import com.example.restaurantmanagementjavaspringboot.viewmodel.AccountInfoViewModel;
import com.example.restaurantmanagementjavaspringboot.viewmodel.AccountListViewModel;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("viewAllAccount")
    @ResponseBody
    public AccountListViewModel viewAllAccountPage(@RequestParam(value = "account_type", required = false , defaultValue = "0") int account_type,
                                                   @RequestParam(value = "page", required = false , defaultValue = "1") int page_number,
                                                   final Model model) {
        AccountListViewModel viewModel = adminService.viewAllAccount(account_type, page_number);
        model.addAttribute("Model", viewModel);

        return viewModel;
//        return "AdminViewAccount";
    }

    @GetMapping("takeAllAccount")
    @ResponseBody
    public Map<String, List<AdminViewAccountDto>> viewAllAccountComponent(@RequestParam(value = "account_type", required = false , defaultValue = "0") int account_type,
                                                                          @RequestParam(value = "page", required = false , defaultValue = "1") int page_number) {

        Map<String, List<AdminViewAccountDto>> map = new HashMap<>();
        map.put("account_list", adminService.takeAllAccount(account_type, page_number));

        return map;
    }

    @GetMapping("viewIndividualAccount")
    @ResponseBody
    public AccountInfoViewModel viewIndividualAccountPage(@RequestParam(value = "account_id") long id,
                                                          final Model model) {
        AccountInfoViewModel viewModel = adminService.viewIndividualAccount(id);
        model.addAttribute("Model", viewModel);

        return viewModel;
//        return "AdminViewAccount";
    }

    @PutMapping("editIndividualAccount")
    public String editIndividualAccount(@RequestBody AdminEditAccountDto accountDto) {
        System.out.println("affected row: " + 0);
        int affectedRow = adminService.editIndividualAccount(accountDto);
        System.out.println("affected row: " + affectedRow);
        return "redirect:viewAllAccount";
//        return "AdminViewAccount";
    }
}
