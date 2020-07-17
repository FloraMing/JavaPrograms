package com.sn.note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.sn.note.dao.NoteDao;
import com.sn.note.dao.ShareDao;
import com.sn.note.entity.Note;
import com.sn.note.entity.NoteResult;
import com.sn.note.entity.Share;
import com.sn.note.util.NoteUtil;

@Service
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao noteDao;

	@Resource
	private ShareDao shareDao;// ����ʼ�

	// ���رʼ��б�
	public NoteResult loadNotes(String bookId) {
		List<Note> notes = noteDao.findByBookId(bookId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("���رʼǳɹ�");
		result.setData(notes);
		return result;
	}

	// ��ӱʼ�
	public NoteResult addNotes(String noteName, String bookId, String userId) {

		NoteResult result = new NoteResult();
		Note checknote= noteDao.findNoteByNoteName(noteName);
		if (checknote!=null) {
			result.setStatus(1);
			result.setMsg("�ʼ����Ѵ���");
			return result;
		}
		    Note note =new Note();
			String cn_note_id = NoteUtil.createId();
			note.setCn_note_id(cn_note_id);
			note.setCn_notebook_id(bookId);
			note.setCn_user_id(userId);
			note.setCn_note_status_id("1");
			note.setCn_note_type_id("1");
			note.setCn_note_body("");
			note.setCn_note_title(noteName);
			note.setCn_note_create_time(System.currentTimeMillis());
			noteDao.addNotes(note);

			// ��ӱʼǳɹ�
			result.setStatus(0);
			result.setMsg("��ӱʼǳɹ�");
			result.setData(cn_note_id);
			return result;
		}
		
	

	// ���ұʼ�id���༭�ʼ�
	public NoteResult findNoteByNoteId(String noteId) {
		Note note = noteDao.findNoteByNoteId(noteId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("���ұʼǳɹ�");
		result.setData(note);
		return result;
	}

	// �����޸ĺ�ıʼ�
	public NoteResult saveNote(String noteId, String noteTitle, String noteBody) {
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());

		noteDao.updateNote(note);

		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("�޸ıʼǳɹ�");
		return result;
	}

	// ɾ���ʼ�
	public NoteResult deleteNote(String noteId) {
		noteDao.deleteNote(noteId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("ɾ���ʼǳɹ�");
		return result;
	}

	// �ƶ��ʼ�
	public NoteResult moveNote(String noteId, String bookId) {
		Note note = new Note();
		note.setCn_note_id(noteId);// ���ñʼ�ID
		note.setCn_notebook_id(bookId);// ���ñʼǱ�ID
		noteDao.dynamicUpdate(note);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("ת�Ʊʼǳɹ�");
		return result;
	}

	// ����ʼ�
	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();

		// ��ѯ�ʼ��Ƿ��Ѿ��������
		Share if_share = shareDao.findShareByNoteId(noteId);

		if (if_share != null) {
			result.setStatus(1);
			result.setMsg("�������Ѿ�����");
			return result;
		}

		Note note = noteDao.findNoteByNoteId(noteId);
		Share share = new Share();
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		share.setCn_note_id(noteId);
		shareDao.shareSave(share);
		result.setStatus(0);
		result.setMsg("����ɹ�");

		return result;
	}

	// ��������ʼ�
	public NoteResult findShareTitle(String value) {
		if (value != null && !"".equals(value)) {
			value = "%" + value + "%";
		} else {
			value = "%";
		}
		List<Share> shares = shareDao.findShareTitle(value);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("�����ɹ�");
		result.setData(shares);
		return result;
	}

	// ��ʾ��������ʼǵ�����
	public NoteResult showShareNote(String shareId) {
		NoteResult result = new NoteResult();
		if (shareId != null) {
			Map list = shareDao.showShareNote(shareId);
			System.out.println(list);
			result.setStatus(0);
			result.setMsg("�鿴�ʼǳɹ�");
			result.setData(list);
			return result;
		}
		result.setStatus(1);
		result.setMsg("idΪ��");
		return result;
	}

	// �������վ�ʼ�
	public NoteResult rollBackNote(String userId) {
		NoteResult result = new NoteResult();
		List<Note> list = noteDao.rollBackNote(userId);
		result.setStatus(0);
		result.setMsg("����վ�ʼ�");
		result.setData(list);
		return result;
	}

	// �ӻ���վ�ָ��ʼ�
	public NoteResult replayNote(String bookId, String noteId) {
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		// note.setCn_note_status_id("1");
		noteDao.replayNote(note);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("�ָ��ʼǳɹ���");
		return result;
	}

	// �ӻ���վ����ɾ���ʼ�
	public NoteResult sureDelete(String noteId) {
		NoteResult result = new NoteResult();
		try {
			// noteDao.deleteByNoteId(noteId);
			noteDao.deleteNotes(new String[] { noteId });
		} catch (Exception e) {
			System.out.println("�������쳣��");
			e.printStackTrace();
		}
		result.setStatus(0);
		result.setMsg("�ʼ�ɾ���ɹ���");
		return result;
	}

}
