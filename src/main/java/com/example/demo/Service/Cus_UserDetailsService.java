package com.example.demo.Service;

import com.example.demo.DAO.Account;
import com.example.demo.Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cus_UserDetailsService {
    @Autowired
    private Account cusAccount;

    public UserEntity loadUserByUsername(int ID) {
        return cusAccount.getByUsername(ID);
    }
}
