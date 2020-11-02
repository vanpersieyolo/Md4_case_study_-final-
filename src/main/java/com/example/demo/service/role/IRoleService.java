package com.example.demo.service.role;

import com.example.demo.model.user.Roles;
import com.example.demo.repo.role.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    Roles findByName(String name);
    List<Roles> findAll();
    Optional<Roles> findById(Long id);
    Roles save(Roles roles);
    void remove(Long id);
}
