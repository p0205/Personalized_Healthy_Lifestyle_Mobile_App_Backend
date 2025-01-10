
package com.utem.healthyLifeStyleApp.service;

import com.utem.healthyLifeStyleApp.dto.RiskAssessmentUserDTO;
import com.utem.healthyLifeStyleApp.dto.UserDTO;

public interface UserService {

	public UserDTO getUserById(Integer id);
	public boolean updateFirstLoginStatus(Integer id, Boolean isFirstLogin);

	public RiskAssessmentUserDTO getUserBasicInfoById(Integer id);
}
