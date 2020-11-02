package com.example.demo.service.role;

import com.example.demo.model.user.Roles;
import com.example.demo.repo.role.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService implements IRoleService {
    @Autowired
    private IRoleRepo roleRepo;
    @Override
    public Roles findByName(String name) {
        return roleRepo.findByNameRole(name);
    }
    @Override
    public List<Roles> findAll() {
        return (List<Roles>) roleRepo.findAll();
    }

    @Override
    public Optional<Roles> findById(Long id) {
        return roleRepo.findById(id);
    }

    @Override
    public Roles save(Roles roles) {
        return roleRepo.save(roles);
    }

    @Override
    public void remove(Long id) {
        roleRepo.deleteById(id);
    }
}
