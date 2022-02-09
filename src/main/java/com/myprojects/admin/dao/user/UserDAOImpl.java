package com.myprojects.admin.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.User;
import com.myprojects.admin.util.user.UserDTO;

@Repository
@Transactional
public class UserDAOImpl extends GenericDaoImpl<User, Long> implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser(Integer code) {
		Criteria c = getCurrentSession().createCriteria(User.class);
		if (code != null) {
			c.add(Restrictions.eq("status", code));
		}
		c.addOrder(Order.asc("name"));
		return c.list();
	}

	@Override
	public User checkLoginNameAndPassword(UserDTO userDTO) {
		Criteria c = getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("userName", userDTO.getUserName().trim()));
		c.add(Restrictions.eq("password", userDTO.getPassword().trim()));
		@SuppressWarnings("unchecked")
		List<User> results = c.list();
		return (CommonUtil.validList(results)) ? results.get(0) : null ;
	}

}
