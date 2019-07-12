package com.vision.erp.service.domain;

public class WorkAttitude {

	//field
	private String workAttitudeNo;
	private String employeeNo;
	private String employeeName;
	private String workAttitudeCodeNo;
	private String workAttitudeCodeName;
	private String workAttitudeTime;
	private String workAttitudeDate;
	private String usageStatusCodeNo;
	
	//constructor
	public WorkAttitude() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//getter, setter
	
	public String getEmployeeNo() {
		return employeeNo;
	}
	public String getWorkAttitudeNo() {
		return workAttitudeNo;
	}

	public void setWorkAttitudeNo(String workAttitudeNo) {
		this.workAttitudeNo = workAttitudeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getWorkAttitudeCodeNo() {
		return workAttitudeCodeNo;
	}
	public void setWorkAttitudeCodeNo(String workAttitudeCodeNo) {
		this.workAttitudeCodeNo = workAttitudeCodeNo;
	}
	public String getWorkAttitudeCodeName() {
		return workAttitudeCodeName;
	}
	public void setWorkAttitudeCodeName(String workAttitudeCodeName) {
		this.workAttitudeCodeName = workAttitudeCodeName;
	}
	public String getWorkAttitudeTime() {
		return workAttitudeTime;
	}
	public void setWorkAttitudeTime(String workAttitudeTime) {
		this.workAttitudeTime = workAttitudeTime;
	}
	public String getWorkAttitudeDate() {
		return workAttitudeDate;
	}
	public void setWorkAttitudeDate(String workAttitudeDate) {
		this.workAttitudeDate = workAttitudeDate;
	}

	
	public String getUsageStatusCodeNo() {
		return usageStatusCodeNo;
	}

	public void setUsageStatusCodeNo(String usageStatusCodeNo) {
		this.usageStatusCodeNo = usageStatusCodeNo;
	}

	@Override
	public String toString() {
		return "WorkAttitude [workAttitudeNo=" + workAttitudeNo + ", employeeNo=" + employeeNo + ", employeeName="
				+ employeeName + ", workAttitudeCodeNo=" + workAttitudeCodeNo + ", workAttitudeCodeName="
				+ workAttitudeCodeName + ", workAttitudeTime=" + workAttitudeTime + ", workAttitudeDate="
				+ workAttitudeDate + ", usageStatusCodeNo=" + usageStatusCodeNo + "]";
	}

	
	
}
