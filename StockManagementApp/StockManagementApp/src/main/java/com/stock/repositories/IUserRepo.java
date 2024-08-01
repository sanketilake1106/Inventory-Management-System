package com.stock.repositories;

import com.stock.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<MyUser, Integer> {

    MyUser getUserByUsernameAndUserContact(String email, String contact);

    MyUser getUserByUsernameAndPassword(String email, String password);

    Optional<MyUser> getMyUserByUsername(String username);

}
