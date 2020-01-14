package com.spring.domain;

import java.util.Date;

import lombok.Data;
@Data
public class BoardVO {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private Date updatedate;
}
