package com.myprojects.admin.dao.note;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.Note;
import com.myprojects.admin.util.note.NoteDTO;

@Repository
@Transactional
public class NoteDAOImpl extends GenericDaoImpl<Note, Long> implements NoteDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Note> searchNote(NoteDTO noteDTO) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Note.class);
		if (noteDTO != null) {
			if (CommonUtil.validString(noteDTO.getItemName())) {
				c.add(Restrictions.like("itemName", noteDTO.getItemName(), MatchMode.ANYWHERE));
			}
			if (noteDTO.getCategoryDTO() != null && CommonUtil.validLong(noteDTO.getCategoryDTO().getId())) {
				c.createAlias("category", "cat", JoinType.INNER_JOIN);
				c.add(Restrictions.eq("cat.id", noteDTO.getCategoryDTO().getId()));
			}
			if (noteDTO.getNotedUser() != null && CommonUtil.validLong(noteDTO.getNotedUser().getId())) {
				c.createAlias("notedUser", "user", JoinType.INNER_JOIN);
				c.add(Restrictions.eq("user.id", noteDTO.getNotedUser().getId()));
			}
		}
		c.addOrder(Order.asc("createdDate"));
		return c.list();
	}

}
