//package com.example.demo.controller;
//
//import com.example.demo.enums.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/role")
//public class RoleController {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @GetMapping
//    public ResponseEntity<List<Role>> readAllRoles(){
//        return new ResponseEntity<>((List<Role>) roleRepository.findAll(), HttpStatus.OK);
//    }
//
//}
