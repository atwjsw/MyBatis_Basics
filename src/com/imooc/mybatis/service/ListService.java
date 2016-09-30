package com.imooc.mybatis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.imooc.mybatis.bean.Command;
import com.imooc.mybatis.bean.CommandContent;
import com.imooc.mybatis.bean.Message;
import com.imooc.mybatis.dao.CommandDao;
import com.imooc.mybatis.dao.ListDao;
import com.imooc.mybatis.entity.Page;
import com.imooc.mybatis.util.IConst;

public class ListService {

	public List<Message> getMessageList(String command, String description) {
		return new ListDao().getMessageList(command, description);
	}

	public List<Message> queryMessageList(String command, String description, Page page) {
		ListDao listDao = new ListDao();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		int totalNumber = listDao.getMessageCount(message);
		page.setTotalNumber(totalNumber);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("page", page);
		List<Message> messageList = listDao.queryMessageList(map);
		for (Message m : messageList) {
			System.out.println(m.getId() + "   " + m.getCommand() + "    " + m.getDescription());
		}
		return messageList;
	}

	public void deleteOne(String id) {

		if (id != null && !id.trim().equals("")) {
			new ListDao().deleteOne(Integer.valueOf(id));
		}

	}

	public void deleteBatch(String[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (String id : ids) {
			idList.add(Integer.valueOf(id));
		}
		new ListDao().deleteBatch(idList);
	}

	public String queryByCommand(String command) {

		CommandDao commandDao = new CommandDao();

		List<Command> commandList;

		if (command.equals(IConst.HELP_COMMAND)) {
			commandList = commandDao.queryCommandList(null, null);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < commandList.size(); i++) {
				sb.append("回复【" + commandList.get(i).getName() + "】可以查看【" + commandList.get(i).getDescription()
						+ "】<br>");
			}
			return sb.toString();
		}

		commandList = commandDao.queryCommandList(command, null);

		if (commandList != null && commandList.size() != 0) {
			List<CommandContent> commandContentList = commandList.get(0).getContentList();
			int i = new Random().nextInt(commandContentList.size());
			return commandContentList.get(i).getContent();
		} else {
			return IConst.NO_MATCHING_CONTENT;
		}
	}
}
