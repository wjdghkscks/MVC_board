package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class OnelistCommand implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터 값
		String idx = request.getParameter("idx");
		String cPage = request.getParameter("cPage");
		
		// 트랜잭션 Transaction : 나눠서 해야 할 일처리를 한 번에 진행
		//	>>> getHit 와 getOnelist 는 transaction 가능함
		
		// hit 업데이트
		DAO.getHit(idx);
		
		// 상세보기
		VO vo = DAO.getOnelist(idx);
		
		// 수정 및 삭제를 위해 세션 저장
		request.getSession().setAttribute("vo", vo);
		
		// cPage 저장
		request.setAttribute("cPage", cPage);
		
		return "view/onelist.jsp";
	}
}
