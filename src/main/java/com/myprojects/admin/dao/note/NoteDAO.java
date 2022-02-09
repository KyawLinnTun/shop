package com.myprojects.admin.dao.note;

import java.util.List;

import com.myprojects.admin.dao.generic.GenericDao;
import com.myprojects.admin.entity.Note;
import com.myprojects.admin.util.note.NoteDTO;

public interface NoteDAO extends GenericDao<Note, Long>{

	List<Note> searchNote(NoteDTO noteDTO);

}
