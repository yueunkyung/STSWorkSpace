package com.shinhan.model;

import java.util.List;

import com.shinhan.dto.DeptVO;
import com.shinhan.dto.JobVO;

//Service : 비지니스로직 담당
//POJO(Plain Old Java Object) : 정해진 규칙없이 자바로 만들어진 class
//계속 사용예정..
public class JobService {
	JobDAO dao = new JobDAO();
	//all
	public List<JobVO> selectAll() {
		return dao.selectAll();	
	}
}
