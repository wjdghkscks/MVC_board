package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateOkCommand implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		try {
			
			// 파일 경로 지정
			String path = request.getServletContext().getRealPath("/upload");
						
			MultipartRequest mr = new MultipartRequest(
						request,
						path,
						100 * 1024 * 1024,
						"UTF-8",
						new DefaultFileRenamePolicy());
			
			String idx = mr.getParameter("idx");
			String cPage = mr.getParameter("cPage");
			String f_name = mr.getParameter("f_name");
			
			// 파라미터 값 받기
			VO vo = new VO();
			
			vo.setWriter(mr.getParameter("writer"));
			vo.setTitle(mr.getParameter("title"));
			vo.setContent(mr.getParameter("content"));
			vo.setPwd(mr.getParameter("pwd"));
			vo.setIdx(idx);
			
			if (mr.getFile("file_name") != null) {
				vo.setFile_name(mr.getFilesystemName("file_name"));
			} else {
				vo.setFile_name(f_name);
			}
			
			int result = DAO.getUpdate(vo);
			
			return "/MyController?cmd=onelist&idx=" + idx + "&cPage=" + cPage;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
}
