package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentDto : 자유 게시판에 작성하는 댓글에 대한 정보")
public class CommentDto {

	@ApiModelProperty(value = "댓글 고유 번호")
	private int comment_no;
	@ApiModelProperty(value = "작성자이름")
	private String user_name;
	@ApiModelProperty(value = "댓글")
	private String comment;
	@ApiModelProperty(value = "작성시각")
	private String comment_time;
	@ApiModelProperty(value = "글번호", example = "2")
	private int articleNo;

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	@Override
	public String toString() {
		return "CommentDto [comment_no=" + comment_no + ", user_name=" + user_name + ", comment=" + comment
				+ ", comment_time=" + comment_time + ", articleNo=" + articleNo + "]";
	}

}
