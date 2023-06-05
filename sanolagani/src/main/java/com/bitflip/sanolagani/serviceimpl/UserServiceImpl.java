package com.bitflip.sanolagani.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitflip.sanolagani.models.User;
import com.bitflip.sanolagani.repository.UserRepo;
import com.bitflip.sanolagani.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userrepo;

	@Override
	public void saveUser(User u) {
		userrepo.save(u);
	}

}
