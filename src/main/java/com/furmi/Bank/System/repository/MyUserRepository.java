package com.furmi.Bank.System.repository;

import com.furmi.Bank.System.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUsername(String username);
}
