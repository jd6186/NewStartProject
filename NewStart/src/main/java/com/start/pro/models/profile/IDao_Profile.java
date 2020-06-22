package com.start.pro.models.profile;

import com.start.pro.dto.DTO_Career;
import com.start.pro.dto.DTO_Profile;

public interface IDao_Profile {

	/**
	 * 자기 프로필 조회
	 * @param user_seq(유저 번호)
	 * @return DTO_Profile
	 */
	public DTO_Profile searchProfile(String user_seq);
	public DTO_Career searchCareer(String user_seq);

	/**
	 * 프로필 등록 
	 * @param pDto(유저번호, 학교, 전공, 기술, 소개, 별점)
	 * @return 입력 성공 시 true, 실패시 false
	 */
	public boolean insertProfile(DTO_Profile pDto);
	
	/**
	 * 경력 등록
	 * @param cDto(유저 번호, 회사, 부서, 직급, 기간)
	 * @return 입력 성공 시 true, 실패시 false
	 */
	public boolean insertCareer(DTO_Career cDto);
	
	/**
	 * 프로필 수정
	 * @param pDto(유저번호, 학교, 전공, 기술, 소개, 별점)
	 * @return 입력 성공 시 true, 실패시 false
	 */
	public boolean updateProfile(DTO_Profile pDto);
	
	/**
	 * 경력 수정
	 * @param cDto(유저 번호, 회사, 부서, 직급, 기간)
	 * @return 입력 성공 시 true, 실패시 false
	 */
	public boolean updateCareer(DTO_Career cDto);
	
	
	public String avgStar(String user_seq);



}
