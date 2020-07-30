package member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.bean.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int memberJoin(MemberDTO memberDTO){		
		return sqlSession.insert("member.memberJoin", memberDTO);
	}
	public MemberDTO memberView(String email) {
		return sqlSession.selectOne("member.memberView", email);
	}
		public List<MemberDTO> memberList(int startNum, int endNum){	
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("startNum", startNum);
			map.put("endNum", endNum);
			return sqlSession.selectList("member.memberList", map);
		}
		public int getTotalA() {		
			return sqlSession.selectOne("member.getTotalA");
		}
		public int memberUpdate(MemberDTO memberDTO) {
			return sqlSession.update("member.memberUpdate", memberDTO);
		}
		public int memberDelete(String email) {
			return sqlSession.delete("member.memberDelete", email);
		}
		public int fileUpload(MemberDTO memberDTO) {
			return sqlSession.update("member.fileUpload", memberDTO);
		}
		
		public int getTotalB() {
			return sqlSession.selectOne("member.getTotalB");
		}
		public int getTotalnewUsers(String day) {
			return sqlSession.selectOne("member.getTotalnewUsers", day);
		}
		public int resumeHit(MemberDTO memberDTO) {
			return sqlSession.update("member.resumeHit", memberDTO);
		}
		public List<MemberDTO> memberResumeList() {
			return sqlSession.selectList("member.memberResumeList");
		}
}
