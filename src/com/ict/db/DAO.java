package com.ict.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sun.org.apache.bcel.internal.generic.Select;

import oracle.jdbc.OracleConnection.CommitOption;

// DB 처리 클래스
public class DAO {

	// MyBatis 는 SqlSession 클래스를 사용
	private static SqlSession ss;
	
	// Singleton 패턴
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			// [Auto commit X]
			//	> Transaction 처리 시 개발자가 수동으로 commit 
			// ss = DBService.getFactory().openSession(false);
			ss = DBService.getFactory().openSession();
			
			// [Auto commit O]
			// ss = DBService.getFactory().openSession(true);
			
			// SELECT 쿼리는 commit 이 필요 없음
			// INSERT, UPDATE, DELETE 는 commit 필요
		}
		return ss;
	}
	
// ↓↓↓ SqlSession 을 이용하여 DB 처리 ↓↓↓
	
	public static int getCount() {
		
		int result = 0;
		
		result = getSession().selectOne("t_count");
		
		return result;
	}
	
	public static List<VO> getList(int begin, int end) {
		
		List<VO> list = null;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		
		list = getSession().selectList("list", map);
		
		return list;
	}
	
	public static int getWrite(VO vo) {
		
		int result = 0;
		
		result = getSession().insert("write", vo);
		
		ss.commit();
		
		return result;
	}
	
// 조회수 처리
	public static void getHit(String idx) {
		
		getSession().update("hitup", idx);
		
		ss.commit();
	}
	
// onelist 조회
	public static VO getOnelist(String idx) {
		
		VO vo = null;
		
		vo = getSession().selectOne("onelist", idx);
		
		return vo;
	}
	
// 업데이트
	public static int getUpdate(VO vo) {
		
		int result = 0;
		
		result = getSession().update("update", vo);
		
		ss.commit();
		
		return result;
	}
	
// 삭제
	public static int getDelete(String idx) {
		
		int result = 0;
		
		result = getSession().delete("delete", idx);
		
		ss.commit();
		
		return result;
	}
	
// 게시글에 달려 있는 댓글 lev 업데이트
	public static void getUpLev(Map<String, Integer> map) {
		
		getSession().update("levup", map);
		
		ss.commit();
	}
	
// 댓글 삽입
	public static int getReWrite(VO vo) {
		
		int result = 0;
		
		result = getSession().insert("re_write", vo);
		
		ss.commit();
		
		return result;
	}
	
	
	
	
}
