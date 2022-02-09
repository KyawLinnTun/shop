package com.myprojects.admin.service.note;

import java.util.List;

import com.myprojects.admin.util.note.NoteDTO;

public interface NoteService {

	NoteDTO getById(Long id);

	NoteDTO saveOrUpdateNote(NoteDTO noteDTO);

	List<NoteDTO> searchNote(NoteDTO noteDTO);

	boolean deleteNoteById(Long id);

}
