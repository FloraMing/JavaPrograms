package com.sn.note.service;

import com.sn.note.entity.NoteResult;

public interface NoteBookService {

	//���رʼǱ��б�
	public NoteResult loadBooks(String userId);
	
	//��ӱʼǱ�
	public NoteResult addNoteBooks(String bookName,String userId);

	//�������ʼǱ�
	public NoteResult renameBook(String bookName,String bookId);

	//ɾ���ʼǱ�
	public NoteResult deleteBook(String bookId);
}
