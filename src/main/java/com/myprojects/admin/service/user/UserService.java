package com.myprojects.admin.service.user;

import java.util.List;

import com.myprojects.admin.util.user.UserDTO;

public interface UserService {

	List<UserDTO> getAllUser(int code);

	UserDTO checkAuth(UserDTO userDTO);

}
