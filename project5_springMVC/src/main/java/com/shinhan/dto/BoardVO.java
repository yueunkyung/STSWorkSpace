package com.shinhan.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BoardVO {
	 int bno;
	 String title;
	 String content;
	 String writer;
	 Date regdate;
	 Date updatedate;
	 int viewcnt;
}
