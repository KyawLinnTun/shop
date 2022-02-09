package com.myprojects.admin.service.note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.note.NoteDAO;
import com.myprojects.admin.entity.Category;
import com.myprojects.admin.entity.Note;
import com.myprojects.admin.entity.User;
import com.myprojects.admin.util.note.NoteDTO;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteDAO noteDao;

	@Override
	@CheckMethod
	public NoteDTO getById(Long id) {
		Note note = noteDao.get(id);
		if (note != null) {
			return new NoteDTO(note);
		}
		return null;
	}

	@Override
	@CheckMethod
	public NoteDTO saveOrUpdateNote(NoteDTO dto) {
		if (dto != null) {
			Note note = new Note();
			if (CommonUtil.validLong(dto.getId())) {
				note = noteDao.get(dto.getId());
			} else {
				note.setCreatedDate(new Date());
			}
			note.setItemName(dto.getItemName());
			note.setSize(dto.getSize());
			note.setColor(dto.getColor());
			note.setRemaining(dto.getRemaining());
			note.setDescription(dto.getDescription());
			note.setBrand(dto.getBrand());
			note.setManufactureCountry(dto.getManufactureCountry());
			if (dto.getCategoryDTO() != null && CommonUtil.validLong(dto.getCategoryDTO().getId())) {
				Category category = new Category();
				category.setId(dto.getCategoryDTO().getId());
				note.setCategory(category);
			}
			if (dto.getNotedUser() != null && CommonUtil.validLong(dto.getNotedUser().getId())) {
				User user = new User();
				user.setId(dto.getNotedUser().getId());
				note.setNotedUser(user);
			}
			noteDao.saveOrUpdate(note);
			return new NoteDTO(note);
		}
		return null;
	}

	@Override
	@CheckMethod
	public List<NoteDTO> searchNote(NoteDTO noteDTO) {
		List<Note> noteList = noteDao.searchNote(noteDTO);
		List<NoteDTO> dtoList = new ArrayList<>();
		if (CommonUtil.validList(noteList)) {
			noteList.stream().forEach(note -> {
				dtoList.add(new NoteDTO(note));
			});
		}
		return dtoList;
	}

	@Override
	@CheckMethod
	public boolean deleteNoteById(Long id) {
		Note note = noteDao.get(id);
		if (note != null) {
			noteDao.delete(note);
			return true;
		}
		return false;
	}
}
