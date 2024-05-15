package com.furmi.Bank.System.repository;

import com.furmi.Bank.System.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser,Long> {
    MyUser findyByUsername (String username);
}
