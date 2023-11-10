package com.shinhan.model;

import java.util.ArrayList;
import java.util.List;

import com.shinhan.dto.EmpVO;

public interface EmpDAOInterface {

	public int empDelete(int empid);

	public int empInsert(EmpVO emp);

	public int empUpdate(EmpVO emp);

	public EmpVO selectById(int empid);

	public List<EmpVO> selectAll(ArrayList<Integer> deptid, String jobid, int sal, String hiredate);

	public List<EmpVO> selectManagerAll();

	public EmpVO loginCheck(String email, int password);
}