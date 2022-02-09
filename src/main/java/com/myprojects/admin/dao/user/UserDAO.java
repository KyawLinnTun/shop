package com.myprojects.admin.dao.user;

import java.util.List;

import com.myprojects.admin.dao.generic.GenericDao;
import com.myprojects.admin.entity.User;
import com.myprojects.admin.util.user.UserDTO;

public interface UserDAO extends GenericDao<User, Long> {

	List<User> getAllUser(Integer code);

	User checkLoginNameAndPassword(UserDTO userDTO);

}
