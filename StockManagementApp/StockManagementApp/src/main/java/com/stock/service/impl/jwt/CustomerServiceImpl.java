package com.stock.service.impl.jwt;

import com.stock.entity.MyUser;
import com.stock.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements UserDetailsService {
    @Autowired
    private IUserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repo.getMyUserByUsername(username);
        if(user.isEmpty()){
            System.out.println("MyUser Not Found!!");
            throw new UsernameNotFoundException("No MyUser Found!!");
        }
        return new User(user.get().getUsername(),user.get().getPassword(),user.get().getAuthorities());
    }
}
