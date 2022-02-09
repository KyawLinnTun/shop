package com.myprojects.admin.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.user.UserDAO;
import com.myprojects.admin.entity.User;
import com.myprojects.admin.util.user.UserDTO;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	public List<UserDTO> getAllUser(int code) {
		List<User> userList = userDao.getAllUser(code);
		List<UserDTO> dtoList = new ArrayList<>();
		if (CommonUtil.validList(userList)) {
			userList.stream().forEach(user -> {
				dtoList.add(new UserDTO(user));
			});
		}
		return dtoList;
	}

	@Override
	public UserDTO checkAuth(UserDTO userDTO) {
		User user = userDao.checkLoginNameAndPassword(userDTO);
		if (user == null) {
			return null;
		}
		return new UserDTO(user);
	}
}
