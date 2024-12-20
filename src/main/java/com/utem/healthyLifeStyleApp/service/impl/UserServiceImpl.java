package com.utem.healthyLifeStyleApp.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.utem.healthyLifeStyleApp.dto.UserDTO;
import com.utem.healthyLifeStyleApp.mapper.UserMapper;
import com.utem.healthyLifeStyleApp.model.User;
import com.utem.healthyLifeStyleApp.repo.UserRepo;
import com.utem.healthyLifeStyleApp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	final private UserRepo userRepo;

	final private UserMapper mapper;

	@Override
	public UserDTO getUserById(Integer id) {
		
		Optional<User> user = userRepo.findById(id);
		if(user.isEmpty())
			return null;
		return mapper.toDTO(user.get());
	}
}
