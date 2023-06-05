package com.bitflip.sanolagani.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bitflip.sanolagani.models.CustomUserDetail;
import com.bitflip.sanolagani.models.User;
import com.bitflip.sanolagani.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	UserRepo userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		// TODO Auto-generated method stub
		User user = userrepo.findByEmail(username);
		System.out.println(user.getFname());
		if (user == null) {
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 8a7b388a6ae8b3d8640cc12480d406e99105cbeb
=======
>>>>>>> 8cb02cfe1ef9f59cee1e34312d8903924f6730c9
			throw new UsernameNotFoundException("user does not exist");
		}
		return new CustomUserDetail(user);
	}

}
