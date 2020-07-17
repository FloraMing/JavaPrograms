package com.sn.note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sn.note.dao.NoteBookDao;
import com.sn.note.dao.NoteDao;
import com.sn.note.entity.Note;
import com.sn.note.entity.NoteBook;
import com.sn.note.entity.NoteResult;
import com.sn.note.util.NoteUtil;

@Service
public class NoteBookServiceImpl implements NoteBookService {
	
	@Resource
	private NoteBookDao bookDao;
	
	@Resource
	private NoteDao noteDao;

	//���رʼǱ�
	public NoteResult loadBooks(String userId) {
		List<NoteBook> books=bookDao.findByUserId(userId);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("���سɹ�");
		result.setData(books);
		return result;
	}

	//��ӱʼǱ�
	public NoteResult addNoteBooks(String bookName, String userId) {
		NoteResult result=new NoteResult();
		NoteBook checkbook=bookDao.findByBookName(bookName);
		if(checkbook!=null){
			result.setStatus(1);
			result.setMsg("�ʼǱ����Ѵ���");
			return result;
		}
		NoteBook book=new NoteBook();
		String bookid=NoteUtil.createId();
		book.setCn_notebook_id(bookid);
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		book.setCn_notebook_type_id("4");
		book.setCn_notebook_desc("Χ��");
		book.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		
		bookDao.save(book);
		result.setStatus(0);
		result.setMsg("��ӱʼǱ��ɹ�");
		result.setData(bookid);
		return result;
		
	}

	//�������ʼǱ�
	public NoteResult renameBook(String bookName,String bookId) {
		NoteBook book=new NoteBook();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name(bookName);
		bookDao.renameBook(book);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("���ıʼǱ����ֳɹ�");
		return result;
	}

	//ɾ���ʼǱ�
	public NoteResult deleteBook(String bookId) {
		NoteResult result=new NoteResult();
		//���жϸñʼǱ����Ƿ��бʼǣ���������ɾ���ʼǱ�
		List<Note> list=noteDao.findByBookId(bookId);
		if(list.isEmpty()){
			bookDao.deleteBook(bookId);
			result.setStatus(0);
			result.setMsg("ɾ���ʼǱ��ɹ�");
			return result;
			
		}
		result.setStatus(1);
		result.setMsg("�ñʼǱ��´��ڱʼǣ�����ɾ���ʼ�");
		return result;
	}

	
}
