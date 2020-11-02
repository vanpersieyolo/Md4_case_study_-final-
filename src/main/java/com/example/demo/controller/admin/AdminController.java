package com.example.demo.controller.admin;

import com.example.demo.model.user.Roles;
import com.example.demo.model.user.Users;
import com.example.demo.service.role.RolesService;
import com.example.demo.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    RolesService rolesService;
    @Autowired
    UsersService usersService;


    @ModelAttribute("roles")
    public Iterable<Roles> allRoles(){
        return rolesService.findAll();
    }

    @GetMapping
    public ModelAndView showList(Optional<String> s, @PageableDefault(3) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("admins/list");
        Page<Users> usersPage = s.isPresent() ? usersService.search(s.get(), pageable): usersService.findAll(pageable);
        modelAndView.addObject("keyword", s.orElse(null));
        modelAndView.addObject("usersPage", usersPage);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("admins/create");
        modelAndView.addObject("users", new Users());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute Users users){
        usersService.save(users);
        ModelAndView modelAndView = new ModelAndView("admins/create");
        modelAndView.addObject("users", new Users());
        modelAndView.addObject("message", "New Users created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        Optional<Users> users = usersService.findById(id);
        ModelAndView modelAndView = new ModelAndView("admins/edit");
        modelAndView.addObject("usersEdit", new Users());
        modelAndView.addObject("usersOld", users.get());

        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("usersEdit") Users usersEdit, @PathVariable("id") Long id){
        Optional<Users> usersOld = usersService.findById(id);
        if(usersOld.isPresent()){
            usersOld.get().setRole(usersEdit.getRole());
        }
        usersService.save(usersOld.get());
        return "redirect:/admins";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        Optional<Users> users = usersService.findById(id);
        ModelAndView modelAndView = new ModelAndView("admins/delete");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteUsers(@ModelAttribute("admins") Users users){
        usersService.delete(users.getId());
        return "redirect:/admins";
    }

//    @PutMapping("/edit/{id}")
//    public ResponseEntity<Users> deleteUsers(@PathVariable Long id, @RequestBody Users users){
//        Optional<Users> users1 = usersService.findById(id);
//        users.setId(id);
//        if (users1.isPresent()){
//            return new ResponseEntity<>(usersService.save(users), HttpStatus.OK);
//
//        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
