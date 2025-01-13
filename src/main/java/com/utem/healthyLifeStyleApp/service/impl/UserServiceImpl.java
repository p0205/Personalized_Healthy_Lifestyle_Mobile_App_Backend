package com.utem.healthyLifeStyleApp.service.impl;

import java.io.IOException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	public void updateProfileImage(int userId, MultipartFile file) throws IOException {
		if (file.getSize() > 5 * 1024 * 1024) {  // Limit to 5 MB
			throw new RuntimeException("File size exceeds the maximum allowed size of 5 MB.");
		}
			
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		user.setProfileImage(file.getBytes());  // Set the profile image as byte array
		userRepo.save(user);
	}

	
    public UserDTO updateUserInfo(int userId, UserDTO userUpdateDTO) {
        Optional<User> userOpt = userRepo.findById(userId);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Update only the fields that are provided in the request
            if (userUpdateDTO.getName() != null) user.setName(userUpdateDTO.getName());
            if (userUpdateDTO.getEmail() != null) user.setEmail(userUpdateDTO.getEmail());
            if (userUpdateDTO.getGender() != '\0') user.setGender(userUpdateDTO.getGender());  // Assuming '\0' means no change
            if (userUpdateDTO.getAge() != null) user.setAge(userUpdateDTO.getAge());
            if (userUpdateDTO.getWeight() != null) user.setWeight(userUpdateDTO.getWeight());
            if (userUpdateDTO.getHeight() != null) user.setHeight(userUpdateDTO.getHeight());
            if (userUpdateDTO.getGoalCalories() != null) user.setGoalCalories(userUpdateDTO.getGoalCalories());
            if (userUpdateDTO.getOccupationType() != null) user.setOccupationType(userUpdateDTO.getOccupationType());
            if (userUpdateDTO.getOccupationTime() != null) user.setOccupationTime(userUpdateDTO.getOccupationTime());
            if (userUpdateDTO.getHealthHistory() != null) user.setHealthHistory(userUpdateDTO.getHealthHistory());
            if (userUpdateDTO.getAreaOfLiving() != null) user.setAreaOfLiving(userUpdateDTO.getAreaOfLiving());
            if (userUpdateDTO.getNoOfFamilyMember() != null) user.setNoOfFamilyMember(userUpdateDTO.getNoOfFamilyMember());
            
            // Save the updated user back to the database
            return mapper.toDTO(userRepo.save(user));
        } else {
            throw new RuntimeException("User not found with id " + userId);
        }
    }

}
