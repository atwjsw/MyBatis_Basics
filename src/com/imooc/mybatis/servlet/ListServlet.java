package com.imooc.mybatis.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.mybatis.entity.Page;
import com.imooc.mybatis.service.ListService;


@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		String currentPage = req.getParameter("currentPage");
		System.out.println("ListServlet:  " + command + "     " + description + "    " + currentPage);
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if (currentPage==null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		req.setAttribute("messageList", new ListService().queryMessageList(command, description, page));
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}

}
