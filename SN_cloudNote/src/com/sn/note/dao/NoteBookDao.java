package com.sn.note.dao;

import java.util.List;

import com.sn.note.entity.NoteBook;

public interface NoteBookDao {

	//�����û�id��ѯ��ǰ�û��ıʼǱ�
	public List<NoteBook> findByUserId(String userId);
	
	//���ݱʼǱ������ұʼǱ��Ƿ��Ѵ���
	public NoteBook findByBookName(String bookName);
	
	//��������
	public void save(NoteBook book);

	//�������ʼǱ�
	public void renameBook(NoteBook book);

	//ɾ���ʼǱ�
	public void deleteBook(String bookId);

	
	
}
