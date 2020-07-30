package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class ListCommand implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
	// 페이징 기법 >> 시작 번호, 끝 번호, 시작 블록, 끝 블록 구하기
		Paging paging = new Paging();
		
	// 1. totalRecord 를 활용하여 totalPage 구하기
		int su = DAO.getCount();
		paging.setTotalRecord(su);
		
		// totalRecord 가 numPerPage 보다 적은/많은 경우 totalPage 처리
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			
			// 나머지가 있으면 1페이지 추가
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		
	// 2. nowPage 구하기 - 처음을 제외한 나머지 리스트로 이동하는 경우에는
	//					   무조건 cPage 파라미터 값을 가져와야 함
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
	// 3. 시작 번호 및 끝 번호 구하기
		paging.setBegin((paging.getNowPage()-1)*paging.getNumPerPage() + 1);
		paging.setEnd((paging.getBegin()-1)+paging.getNumPerPage());
		
	// 4. 시작 블록 및 끝 블록 구하기
		paging.setBeginBlock((int)((paging.getNowPage()-1)/paging.getPagePerBlock())*paging.getPagePerBlock()+1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock()-1);
		
		// endBlock 이 totalPage 보다 큰 경우, 불필요한 endBlock 이 생성되는 문제 발생
		// 따라서 endBlock > totalPage 인 경우, endBlock 의 값을 totalPage 로 변경
			if (paging.getEndBlock() > paging.getTotalPage()) {
				paging.setEndBlock(paging.getTotalPage());
			}
		
	// 5. DB 처리
		List<VO> list = DAO.getList(paging.getBegin(), paging.getEnd());
		
		request.setAttribute("list", list);
		
		request.setAttribute("paging", paging);
		
		return "view/list.jsp";
	}
}
