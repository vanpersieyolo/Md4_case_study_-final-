package com.example.demo.service.user;

import com.example.demo.model.user.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IUsersService {
    Iterable<Users> findAll();
    Optional<Users> findById(Long id);
    void save (Users users);
    void remove (Long id);
    Users findByUsersName(String name);

    Page<Users> search(String keyword, Pageable pageable);

    void delete(Long id);
    Page<Users> findAll(Pageable pageable);


}
