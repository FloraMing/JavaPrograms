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
	private ShareDao shareDao;// 分享笔记

	// 加载笔记列表
	public NoteResult loadNotes(String bookId) {
		List<Note> notes = noteDao.findByBookId(bookId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("加载笔记成功");
		result.setData(notes);
		return result;
	}

	// 添加笔记
	public NoteResult addNotes(String noteName, String bookId, String userId) {

		NoteResult result = new NoteResult();
		Note checknote= noteDao.findNoteByNoteName(noteName);
		if (checknote!=null) {
			result.setStatus(1);
			result.setMsg("笔记名已存在");
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

			// 添加笔记成功
			result.setStatus(0);
			result.setMsg("添加笔记成功");
			result.setData(cn_note_id);
			return result;
		}
		
	

	// 查找笔记id，编辑笔记
	public NoteResult findNoteByNoteId(String noteId) {
		Note note = noteDao.findNoteByNoteId(noteId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("查找笔记成功");
		result.setData(note);
		return result;
	}

	// 保存修改后的笔记
	public NoteResult saveNote(String noteId, String noteTitle, String noteBody) {
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());

		noteDao.updateNote(note);

		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("修改笔记成功");
		return result;
	}

	// 删除笔记
	public NoteResult deleteNote(String noteId) {
		noteDao.deleteNote(noteId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("删除笔记成功");
		return result;
	}

	// 移动笔记
	public NoteResult moveNote(String noteId, String bookId) {
		Note note = new Note();
		note.setCn_note_id(noteId);// 设置笔记ID
		note.setCn_notebook_id(bookId);// 设置笔记本ID
		noteDao.dynamicUpdate(note);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("转移笔记成功");
		return result;
	}

	// 分享笔记
	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();

		// 查询笔记是否已经被分享过
		Share if_share = shareDao.findShareByNoteId(noteId);

		if (if_share != null) {
			result.setStatus(1);
			result.setMsg("分享区已经存在");
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
		result.setMsg("分享成功");

		return result;
	}

	// 搜索分享笔记
	public NoteResult findShareTitle(String value) {
		if (value != null && !"".equals(value)) {
			value = "%" + value + "%";
		} else {
			value = "%";
		}
		List<Share> shares = shareDao.findShareTitle(value);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("搜索成功");
		result.setData(shares);
		return result;
	}

	// 显示搜索分享笔记的内容
	public NoteResult showShareNote(String shareId) {
		NoteResult result = new NoteResult();
		if (shareId != null) {
			Map list = shareDao.showShareNote(shareId);
			System.out.println(list);
			result.setStatus(0);
			result.setMsg("查看笔记成功");
			result.setData(list);
			return result;
		}
		result.setStatus(1);
		result.setMsg("id为空");
		return result;
	}

	// 处理回收站笔记
	public NoteResult rollBackNote(String userId) {
		NoteResult result = new NoteResult();
		List<Note> list = noteDao.rollBackNote(userId);
		result.setStatus(0);
		result.setMsg("回收站笔记");
		result.setData(list);
		return result;
	}

	// 从回收站恢复笔记
	public NoteResult replayNote(String bookId, String noteId) {
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		// note.setCn_note_status_id("1");
		noteDao.replayNote(note);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("恢复笔记成功！");
		return result;
	}

	// 从回收站彻底删除笔记
	public NoteResult sureDelete(String noteId) {
		NoteResult result = new NoteResult();
		try {
			// noteDao.deleteByNoteId(noteId);
			noteDao.deleteNotes(new String[] { noteId });
		} catch (Exception e) {
			System.out.println("这里有异常！");
			e.printStackTrace();
		}
		result.setStatus(0);
		result.setMsg("笔记删除成功！");
		return result;
	}

}
