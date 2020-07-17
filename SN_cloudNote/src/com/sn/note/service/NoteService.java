package com.sn.note.service;

import com.sn.note.entity.NoteResult;

public interface NoteService {

	//���رʼ�
	public NoteResult loadNotes(String bookId);

	//��ӱʼ�
	public NoteResult addNotes(String noteName, String bookId, String userId);

	//���ݱʼ�id���ұʼǣ��༭������Ӧ�ı����ݣ�
	public NoteResult findNoteByNoteId(String noteId);

	//�༭�ʼ����ݱ���
	public NoteResult saveNote(String noteId, String noteTitle, String noteBody);

	//ɾ���ʼ�
	public NoteResult deleteNote(String noteId);

	//�ƶ��ʼ�
	public NoteResult moveNote(String noteId,String bookId);

	//����ʼ�
	public NoteResult shareNote(String noteId);

	//��������ʼ�
	public NoteResult findShareTitle(String value);

	//��ʾ��������ʼǵ�����
	public NoteResult showShareNote(String shareId);

	//�������վ�ʼ�
	public NoteResult rollBackNote(String userId);

	//�ӻ���վ�ָ��ʼ�
	public NoteResult replayNote(String bookId, String noteId);

	//�ӻ���վ����ɾ���ʼ�
	public NoteResult sureDelete(String noteId);

}
