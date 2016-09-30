package com.imooc.mybatis.dao;
/*
 * 与message.xml对应的接口类
 * 
 */
import java.util.List;
import java.util.Map;

import com.imooc.mybatis.bean.Message;

public interface IMessage {
	
	public List<Message> queryMessageList(Message message);	
	
	public List<Message> queryMessageList(Map<String, Object> parameter);	
	
	public int count(Message message);

}
