package com.edmond.securtiy.service;

import com.edmond.securtiy.entity.User;
import com.edmond.securtiy.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j //log
public class UserService {

    private final UserRepo userRepo;

    public User findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public List<User> findUserList(){
        return userRepo.findAll();
    }

    public void addUser(User newUser){
        User user = findByEmail(newUser.getEmail());

        if(user == null){
            log.info("----- New user has been added -----");
            userRepo.save(newUser);
        }else{
            throw new IllegalStateException("User exist!");
        }
    }

    public void updateUser(User user){
        userRepo.save(user);
    }

    public void deleteUserByEmail(String email){
        userRepo.deleteByEmail(email);
    }
}
