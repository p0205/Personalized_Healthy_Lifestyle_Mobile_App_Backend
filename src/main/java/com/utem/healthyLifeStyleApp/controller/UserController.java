package com.utem.healthyLifeStyleApp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utem.healthyLifeStyleApp.dto.RiskAssessmentUserDTO;
import com.utem.healthyLifeStyleApp.dto.UserDTO;
import com.utem.healthyLifeStyleApp.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	final private UserServiceImpl userService;
	
	 @GetMapping("/{id}")
	 public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
		 return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
	 }

	 @GetMapping("/{id}/weight")
	 public ResponseEntity<Double> getUserWeightById(@PathVariable("id") Integer id) {
		 return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id).getWeight());
	 }

	@PatchMapping("/{id}/first-login")
	public ResponseEntity<Void> setFirstLogin(@PathVariable("id") Integer id, @RequestBody Map<String, Boolean> payload) {
		Boolean isFirstLogin = payload.get("isFirstLogin");
		if (isFirstLogin == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Handle missing value
		}
		boolean updated = userService.updateFirstLoginStatus(id, isFirstLogin);
		if (updated) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/{id}/riskAssessment")
	public ResponseEntity<RiskAssessmentUserDTO> get(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserBasicInfoById(id));
	}
	


	@PostMapping("/uploadProfileImage/{userId}")
	public ResponseEntity<String> uploadProfileImage(@PathVariable int userId, 
	                                                 @RequestParam("file") MultipartFile file) {
		try {
			userService.updateProfileImage(userId, file);
			return ResponseEntity.ok("Profile image uploaded successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
		
		}
	}

	@PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUserInfo(@PathVariable int userId, @RequestBody UserDTO userUpdateDTO) {
        return ResponseEntity.ok(userService.updateUserInfo(userId, userUpdateDTO));
    }

}
