package kr.co.sist.prj3.user.profile.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.user.profile.domain.MyProfileDomain;
import kr.co.sist.prj3.user.profile.vo.ProfileModifyVO;

@Component
public class ProfileDAO {

	
	/**
	 * �г������� �˻� 
	 * @param nickName
	 * @return
	 */
	public String selectChkNick (String nick_name) {
		
		
		String selectedNick="";
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2.�ڵ鷯 ����ϱ� - ������ �����ϱ�
		selectedNick = ss.selectOne("selectNickDup", nick_name);
		
		//3.��ȸ��� ó��
		//4.MyBatis Handler �ݱ�
		if(ss!=null) {ss.close();}
		
		return selectedNick;
	
	}//selectChkNick

	
	
	
	/**
	 * ������, �г���, �ڱ�Ұ� ����
	 * @param pVO
	 * @return
	 */
	public int updateProfile(ProfileModifyVO pVO) {

		int cnt=0;
		
		// 1.MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		// 2. ����method ȣ���Ͽ� ��� ���

		cnt = ss.update("updateProfile", pVO);
		System.out.println(cnt);
		// 3.transaction ó�� - �� ������ ����� �� �ִ� ���� �ִ� 1��

		if (cnt == 1) {
			ss.commit();
		}

		// 4. �ڵ鷯 �ݱ�

		if (ss != null) {
			ss.close();
		}

		return cnt;

	}// updateProfile
	
	
	
/*	public String selectProfileMsg(String user_id) {
		
		String profileMsg = "";
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		profileMsg = ss.selectOne("selectProfileMsg", user_id);		
		
				
		//4. ���� ����
		if(ss!=null) {ss.close();}//end if
				
		return profileMsg;
		
		
	}//selectProfileMsg
*/	
	
	
	public MyProfileDomain selectProfile(String user_id) {
		
		MyProfileDomain profile = null;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		profile = ss.selectOne("selectProfile", user_id);		
		
				
		//4. ���� ����
		if(ss!=null) {ss.close();}//end if
				
		return profile;
		
		
	}//selectProfile
	
	
	
}//class