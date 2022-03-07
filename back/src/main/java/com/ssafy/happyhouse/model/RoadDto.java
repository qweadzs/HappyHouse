package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RoadDto : 산책 정보", description = "산책의 이름을 나타낸다.")
public class RoadDto {
	@ApiModelProperty(value = "코스 카테고리 ")
	private int cosNum;
	@ApiModelProperty(value = "코스 카테고리명 ")
	private String sidoName;
	@ApiModelProperty(value = "강북강남구분코드")
	private int divideCode;
	@ApiModelProperty(value = "강북강남구분")
	private String divide;
	@ApiModelProperty(value = "자치구")
	private String sido;
	@ApiModelProperty(value = "거리")
	private String degree;
	@ApiModelProperty(value = "소요시간")
	private String times;
	@ApiModelProperty(value = "코스레벨")
	private int cosLevel;
	@ApiModelProperty(value = "추천수")
	private int hit;
	@ApiModelProperty(value = "연계지하철")
	private String subway;
	@ApiModelProperty(value = "설명")
	private String explains;
	@ApiModelProperty(value = "pdf파일경로")
	private String pdf;
	@ApiModelProperty(value = "코스명")
	private String cosName;
	@ApiModelProperty(value = "세부코스")
	private String detailCos;
	@ApiModelProperty(value = "포인트순번")
	private int pointNum;
	@ApiModelProperty(value = "포인트명칭")
	private String pointName;
	@ApiModelProperty(value = "x좌표")
	private double xPoint;
	@ApiModelProperty(value = "y좌표")
	private double yPoint;
	@ApiModelProperty(value = "포인트 설명")
	private String pointDescript;
	
	public int getCosNum() {
		return cosNum;
	}
	public void setCosNum(int cosNum) {
		this.cosNum = cosNum;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public int getDivideCode() {
		return divideCode;
	}
	public void setDivideCode(int divideCode) {
		this.divideCode = divideCode;
	}
	public String getDivide() {
		return divide;
	}
	public void setDivide(String divide) {
		this.divide = divide;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getTimes() {
		return times;
	}
	public void setTime(String times) {
		this.times = times;
	}
	public int getCosLevel() {
		return cosLevel;
	}
	public void setCosLevel(int cosLevel) {
		this.cosLevel = cosLevel;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getSubway() {
		return subway;
	}
	public void setSubway(String subway) {
		this.subway = subway;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	public String getCosName() {
		return cosName;
	}
	public void setCosName(String cosName) {
		this.cosName = cosName;
	}
	public String getDetailCos() {
		return detailCos;
	}
	public void setDetailCos(String detailCos) {
		this.detailCos = detailCos;
	}
	public int getPointNum() {
		return pointNum;
	}
	public void setPointNum(int pointNum) {
		this.pointNum = pointNum;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public double getxPoint() {
		return xPoint;
	}
	public void setxPoint(double xPoint) {
		this.xPoint = xPoint;
	}
	public double getyPoint() {
		return yPoint;
	}
	public void setyPoint(double yPoint) {
		this.yPoint = yPoint;
	}
	public String getPointDescript() {
		return pointDescript;
	}
	public void setPointDescript(String pointDescript) {
		this.pointDescript = pointDescript;
	}
	

}
