package com.nuc.a4q.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;

public class CourseDaoTest extends BaseTest {
	@Autowired
	private CourseDao dao;

	@Test
	@Ignore
	public void insertCourseTest() {
		Course course = new Course();
		course.setCourseName("demo");
		course.setCreateTime(new Date());
		course.setLastEditTime(new Date());
		Integer integer = dao.insertCourse(course);
		System.out.println(integer);
	}

	@Test
	@Ignore
	public void updateCourseTest() {
		Course course = new Course();
		PersonInfo per = new PersonInfo();
		per.setUserId(2);
		course.setCourseId(3);
		course.setCourseName("Java");
		course.setCreateTime(new Date());
		course.setLastEditTime(new Date());
		Integer integer = dao.updateCourse(course);
		System.out.println(integer);
	}

	@Test
	@Ignore
	public void deleteCourseTest() {
		Course course = new Course();
		course.setCourseId(5);
		Integer integer = dao.deleteCourse(course);
		System.out.println(integer);
	}

	@Test
	public void queryCourseListTest() {
		Course course = new Course();
		List<Course> list = dao.queryCourseList(course);
		System.out.println(list.size());
	}

	@Test
	@Ignore
	public void queryCourseListNameTest() {
		List<Course> list = dao.queryCourseListName();
		System.out.println(list);
	}
	
	@Test
	@Ignore
	public void getCourseByCourseNameTest() {
		Integer result = dao.getCourseByCourseName("离散");
		System.out.println(result);
	}
}
