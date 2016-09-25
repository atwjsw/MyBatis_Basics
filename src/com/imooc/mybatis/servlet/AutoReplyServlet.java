package com.imooc.mybatis.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.mybatis.service.ListService;

@SuppressWarnings("serial")
public class AutoReplyServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		String command = req.getParameter("content");
		System.out.println("AutoReplyServlet:  " + command);
		PrintWriter out=resp.getWriter();
		out.write(new ListService().queryByCommand(command));
		out.flush();
		out.close();	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}

}
