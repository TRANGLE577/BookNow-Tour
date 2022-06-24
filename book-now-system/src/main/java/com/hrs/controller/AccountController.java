package com.hrs.controller;

import com.hrs.model.dto.AccountDTO;
import com.hrs.model.dto.LoginDTO;
import com.hrs.model.dto.TourDTO;
import com.hrs.model.reponse.AccountResponse;
import com.hrs.model.reponse.RoleResponse;
import com.hrs.service.AccountService;
import com.hrs.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/masterdata/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ErrorUtils errorUtils;

    @GetMapping(value = { "", "/"})
    public String viewHomePage(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        model.addAttribute("accounts", accountService.getList(token));
        model.addAttribute("errors", new HashMap<>());
        return "account";
    }

    @GetMapping(value = { "/form" })
    public String create(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        model.addAttribute("accountDTO", new AccountDTO());
        model.addAttribute("roles", getDataRoles());
        model.addAttribute("errors", new HashMap<>());
        return "account_form";
    }


    @GetMapping(value = { "/form/{id}" })
    public String edit(Model model, HttpSession session, @PathVariable long id) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        AccountResponse response = accountService.getById(token, id);
        if (response == null) {
            response = new AccountResponse();
        }
        model.addAttribute("accountDTO", response);

        model.addAttribute("roles", getDataRoles());
        model.addAttribute("errors", new HashMap<>());
        return "account_form";
    }

    @PostMapping(value = "/form/save")
    public String save(Model model, HttpSession session, @Valid AccountDTO accountDTO, BindingResult bindingResult) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        Map<String, String> errorList = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errorUtils.loadErrorList(bindingResult, errorList);
        } else {
            if (accountDTO.getId() > 0) {
                accountDTO.setUsername(accountDTO.getUsername());
                accountDTO.setFullname(accountDTO.getFullname());
                accountDTO.setEmail(accountDTO.getEmail());
                accountService.save(accountDTO, token);
            } else {

                accountDTO.setPassword("12345");
                accountService.register(accountDTO);
            }
        }

        if (CollectionUtils.isEmpty(errorList)) {
            errorList.put("Success", "Update Complete!");
        }
        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("roles", getDataRoles());
        model.addAttribute("errors", errorList);

        return "redirect:/masterdata/accounts";
    }

    @GetMapping(value = { "/form/delete/{id}" })
    public String delete(Model model, HttpSession session, @PathVariable long id) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(id);
        accountDTO.setStatus(false);
        accountService.deleteAccount(token, accountDTO);

        return "redirect:/masterdata/accounts/";
    }


    private List<RoleResponse> getDataRoles(){
        RoleResponse role = new RoleResponse();
        role.setId(1);
        role.setName("ADMIN");
        RoleResponse role2 = new RoleResponse();
        role2.setId(2);
        role2.setName("USER");
        List<RoleResponse> roles = new ArrayList<>();
        roles.add(role);
        roles.add(role2);
        return roles;
    }

}
