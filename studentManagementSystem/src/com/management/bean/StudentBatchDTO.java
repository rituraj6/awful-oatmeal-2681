package com.management.bean;

public class StudentBatchDTO {

	private int roll;
	private int bId;
	private String cName;

	public StudentBatchDTO(int roll, int bId, String cName) {
		super();
		this.roll = roll;
		this.bId = bId;
		this.cName = cName;
	}

	public StudentBatchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	@Override
	public String toString() {
		return "StudentBatchDTO [roll=" + roll + ", bId=" + bId + ", cName=" + cName + "]";
	}

}
