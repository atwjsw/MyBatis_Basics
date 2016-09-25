package com.imooc.mybatis.db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;

public class DBAccess {
	
	public SqlSession getSqlSession() throws IOException {
		//使用配置文件获取数据库连接信息
		Reader reader = Resources.getResourceAsReader("com/imooc/mybatis/config/Configuration.xml");
		//通过配置信息构建一个SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//通过sqlSessionFactory打开一个数据库会话
		SqlSession  sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}

}
