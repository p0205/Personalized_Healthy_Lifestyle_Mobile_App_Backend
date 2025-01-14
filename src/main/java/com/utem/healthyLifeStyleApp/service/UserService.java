
package com.utem.healthyLifeStyleApp.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.utem.healthyLifeStyleApp.dto.RiskAssessmentUserDTO;
import com.utem.healthyLifeStyleApp.dto.UserDTO;
public interface UserService {

	public UserDTO getUserById(Integer id);
	public boolean updateFirstLoginStatus(Integer id, Boolean isFirstLogin);

	public void updateProfileImage(int userId, MultipartFile file) throws IOException;
	public RiskAssessmentUserDTO getUserBasicInfoById(Integer id);
	public UserDTO updateUserInfo(int userId, UserDTO userUpdateDTO);
}
