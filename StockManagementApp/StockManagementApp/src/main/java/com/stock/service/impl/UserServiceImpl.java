package com.stock.service.impl;

import com.stock.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.repositories.IUserRepo;
import com.stock.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepo repo;

	@Override
	public MyUser saveUser(MyUser myUser) {
		String email = myUser.getUsername();
		String userContact = myUser.getUserContact();
		MyUser b = repo.getUserByUsernameAndUserContact(email, userContact);
		if(b==null){
			return repo.save(myUser);
		}
		return null;
	}

	@Override
	public MyUser fetchUserById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public MyUser isUserPreset(String email, String contact) {
		return repo.getUserByUsernameAndUserContact(email, contact);
	}

	@Override
	public MyUser loginUser(MyUser myUser) {

		MyUser us=repo.getUserByUsernameAndPassword(myUser.getUsername(), myUser.getPassword());

        return us;
	}
}
