package com.bitflip.sanolagani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitflip.sanolagani.models.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
