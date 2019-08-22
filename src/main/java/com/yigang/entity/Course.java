
package com.yigang.entity;

public class Course {

	private int id;
	
	private String courseName;
	
	private String courseNum;
	
	private String teacherName;
	
	private String place;
	
	private int weekBegin;
	
	private int weekEnd;
	
	private int weekType;
	
	private int lesson;
	
	private int day;
	
	private int placeBuilder;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getWeekBegin() {
		return weekBegin;
	}

	public void setWeekBegin(int weekBegin) {
		this.weekBegin = weekBegin;
	}

	public int getWeekEnd() {
		return weekEnd;
	}

	public void setWeekEnd(int weekEnd) {
		this.weekEnd = weekEnd;
	}

	public int getWeekType() {
		return weekType;
	}

	public void setWeekType(int weekType) {
		this.weekType = weekType;
	}

	public int getLesson() {
		return lesson;
	}

	public void setLesson(int lesson) {
		this.lesson = lesson;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getPlaceBuilder() {
		return placeBuilder;
	}

	public void setPlaceBuilder(int placeBuilder) {
		this.placeBuilder = placeBuilder;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", courseNum=" + courseNum + ", teacherName="
				+ teacherName + ", place=" + place + ", weekBegin=" + weekBegin + ", weekEnd=" + weekEnd + ", weekType="
				+ weekType + ", lesson=" + lesson + ", day=" + day + ", placeBuilder=" + placeBuilder + "]";
	}
	
	
}