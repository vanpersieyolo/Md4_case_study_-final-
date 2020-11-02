package com.example.demo.service.user;

import com.example.demo.model.user.Users;
import com.example.demo.repo.blog.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService implements IUsersService {

    @Autowired
    private IUsersRepo usersRepo;
    @Override
    public Iterable<Users> findAll() {
        return usersRepo.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return usersRepo.findById(id);
    }

    @Override
    public void save(Users users) {
        usersRepo.save(users);
    }

    @Override
    public void remove(Long id) {
        usersRepo.deleteById(id);
    }

    @Override
    public Users findByUsersName(String name) {
        return usersRepo.findByUserName(name);
    }
    @Override
    public Page<Users> search(String keyword, Pageable pageable) {
        return usersRepo.findAllByUserNameContains(keyword, pageable);
    }

    @Override
    public void delete(Long id) {
        usersRepo.deleteById(id);
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return usersRepo.findAll(pageable);
    }


}
