package com.imooc.mybatis.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.imooc.mybatis.bean.Message;
import com.imooc.mybatis.db.DBAccess;
import com.imooc.mybatis.entity.Page;

public class ListDao {

	public void deleteOne(int id) {

		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("Message.deleteOne", id);
			sqlSession.commit();
			System.out.println("sqlSession: " + sqlSession);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	public void deleteBatch(List<Integer> idList) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("Message.deleteBatch", idList);
			sqlSession.commit();
			System.out.println("deleteBatch: " + sqlSession);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	public List<Message> getMessageList(String command, String description) {

		List<Message> messageList = new ArrayList<Message>();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		DBAccess dbAccess = new DBAccess();
		IMessage iMessage;
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			//messageList = sqlSession.selectList("Message.queryMessageList", message);
			iMessage = sqlSession.getMapper(IMessage.class);
			messageList = iMessage.queryMessageList(message);
			System.out.println("sqlSession: " + sqlSession);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	public List<Message> queryMessageList(Map<String,Object> parameter) {
		
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<Message>();
		IMessage iMessage;
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			iMessage = sqlSession.getMapper(IMessage.class);						
			messageList = iMessage.queryMessageList(parameter);
			System.out.println("sqlSession: " + sqlSession);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	public int getMessageCount(Message message) {

		DBAccess dbAccess = new DBAccess();
		IMessage iMessage;
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = dbAccess.getSqlSession();
			//messageList = sqlSession.selectList("Message.queryMessageList", message);
			iMessage = sqlSession.getMapper(IMessage.class);
			count = iMessage.count(message);
			System.out.println("sqlSession: " + sqlSession);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return count;
	}

	public List<Message> getMessageListByJDBC(String command, String description) {

		List<Message> messageList = new ArrayList<Message>();
		List<String> paramList = new ArrayList<String>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wechat", "atwjsw", "123456");
			StringBuilder sql = new StringBuilder("SELECT ID, COMMAND, DESCRIPTION, CONTENT FROM message WHERE 1=1");

			if (command != null && !command.trim().equals("")) {
				sql.append(" AND command=?");
				paramList.add(command);
			}

			if (description != null && !description.trim().equals("")) {
				sql.append(" AND description like '%' ? '%'");
				paramList.add(description);
			}
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < paramList.size(); i++) {
				ps.setString(i + 1, paramList.get(i));
			}
			;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTIOn"));
				message.setContent(rs.getString("CONTENT"));
				messageList.add(message);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return messageList;
	}

	public static void main(String[] args) {
		
//		List<Message> messageList = listDao.getMessageList("", "");
//		for (Message message: messageList) {
//			System.out.println(message.getId() + "   " + message.getCommand() + "    " + message.getDescription());
//		}
		
		/*List<Integer> idList = new ArrayList<Integer>();
		idList.add(10);
		idList.add(11);
		listDao.deleteBatch(idList);*/
		ListDao listDao = new ListDao();
		Message message = new Message();
		message.setCommand("");
		message.setDescription("");
		Page page = new Page();		
		page.setCurrentPage(2);	
		page.setTotalNumber(7);	
		page.count();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("page", page);
		List<Message> messageList = listDao.queryMessageList(map);
		for (Message m: messageList) {
			System.out.println(m.getId() + "   " + m.getCommand() + "    " + m.getDescription());
		}

	}

}
