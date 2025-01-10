package com.utem.healthyLifeStyleApp.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.utem.healthyLifeStyleApp.dto.RiskAssessmentUserDTO;
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

	public RiskAssessmentUserDTO getUserBasicInfoById(Integer id) {

		Optional<User> user = userRepo.findById(id);
		if(user.isEmpty())
			return null;
		return RiskAssessmentUserDTO.builder()
				.id(user.get().getId())
				.gender(user.get().getGender())
				.age(user.get().getAge())
				.height(user.get().getHeight())
				.weight(user.get().getWeight())
				.bmi(calculateBMI(user.get().getWeight(), user.get().getHeight()))
				.healthHistory(user.get().getHealthHistory())
				.occupationType(user.get().getOccupationType())
				.name(user.get().getName())
				.build();
	}

	private Double calculateBMI(Double weight, Double height) {
		double h = height / 100;
		return weight / (h * h);
	}
	
	public boolean updateFirstLoginStatus(Integer id, Boolean isFirstLogin) {
		Optional<User> userOptional = userRepo.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setIsFirstLogin(isFirstLogin); // Update based on frontend input
			userRepo.save(user);
			return true;
		}
		return false;
    }
}
