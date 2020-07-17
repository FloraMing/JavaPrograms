package com.sn.note.dao;

import java.util.List;
import java.util.Map;

import com.sn.note.entity.Note;

public interface NoteDao {

	//���رʼ��б�
	public List<Note> findByBookId(String bookId);

	//���ұʼ�����������ӱʼ�ʱ�ʼ����ظ�
	public Note findNoteByNoteName(String noteName);
	
	//��ӱʼ�
	public void addNotes(Note note);

	//���ұʼ�id���༭�ʼ�
	public Note findNoteByNoteId(String noteId);

	//�޸ıʼ�
	public void updateNote(Note note);

	//ɾ���ʼǣ�ʵ�ʷ������վ��
	public void deleteNote(String noteId);

	//�ƶ��ʼǣ���
	public void dynamicUpdate(Note note);

	//����վ�ʼ�
	public List<Note> rollBackNote(String userId);

	//�ӻ���վ�ָ��ʼ�
	public void replayNote(Note note);

	//�ӻ���վ����ɾ���ʼ�
	public void deleteNotes(String[] strings);

	


	

}
