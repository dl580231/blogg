package com.nuc.a4q.dao;

import java.util.List;

import com.nuc.a4q.entity.Course;

public interface CourseDao {
	/**
	 * 增加课程
	 * 
	 * @return
	 */
	public Integer insertCourse(Course course);

	/**
	 * 修改课程
	 * 
	 * @param course
	 * @return
	 */
	public Integer updateCourse(Course course);

	/**
	 * 删除课程
	 * 
	 * @param course
	 * @return
	 */
	public Integer deleteCourse(Course course);

	/**
	 * 模糊查询
	 */
	public List<Course> queryCourseList(Course course);
	
	/**
	 * 查询所有课程名字和id,用于select展示
	 * @return
	 */
	public List<Course> queryCourseListName();

	/**
	 * 通过课程名称判断课程信息是否存在
	 * @param courseName
	 * @return
	 */
	public Integer getCourseByCourseName(String courseName);

}
