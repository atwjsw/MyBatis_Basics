package com.imooc.mybatis.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.imooc.mybatis.bean.Command;
import com.imooc.mybatis.bean.CommandContent;
import com.imooc.mybatis.bean.Message;
import com.imooc.mybatis.db.DBAccess;

public class CommandDao {
	
	public List<Command> queryCommandList(String name, String description) {
		
		List<Command> commandList = new ArrayList<Command> ();
		Command command = new Command();
		command.setName(name);
		command.setDescription(description);
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			commandList = sqlSession.selectList("Command.queryCommandList", command);
			System.out.println("sqlSession: " + sqlSession);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return commandList;
	}
	
	public static void main(String[] args) {
		CommandDao commandDao = new CommandDao();
		List<Command> commandList = commandDao.queryCommandList("段子", "");
		List<CommandContent> contentList = commandList.get(0).getContentList();
		for (CommandContent commandContent: contentList) {
			System.out.println(commandContent.getContent());
			System.out.println(commandContent.getId());
			System.out.println(commandContent.getCommandId());
		}

	}

}
