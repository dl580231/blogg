package com.nuc.a4q.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.a4q.dao.CourseDao;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.exception.LogicException;

@Service
public class CourseService {
	@Autowired
	private CourseDao dao;

	/**
	 * 查询所有的课程名称
	 * 
	 * @return
	 */
	public List<Course> getCourseListName() {
		List<Course> list = dao.queryCourseListName();
		return list;
	}

	/**
	 * 根据条件查询课程列表
	 * 
	 * @param course
	 * @return
	 */
	public List<Course> getCourseList(Course course) {
		List<Course> list = dao.queryCourseList(course);
		return list;
	}

	/**
	 * 根据条件删除用户信息
	 * 
	 * @param course
	 */
	public void removeCurse(Course course) {
		dao.deleteCourse(course);
	}

	/**
	 * 更新课程信息
	 * 
	 * @param course
	 */
	public void updateCourse(Course course) {
		course.setLastEditTime(new Date());
		dao.updateCourse(course);
	}

	/**
	 * 增加课程信息
	 * 
	 * @param course
	 */
	public void addCourse(Course course) {
		// 判断课程信息存在不存在
		Integer result = getCourseByCourseName(course.getCourseName());
		if (result == null) {
			course.setCreateTime(new Date());
			course.setLastEditTime(new Date());
			dao.insertCourse(course);
		} else {
			throw new LogicException("课程信息已经存在");
		}
	}

	/**
	 * 通过课程名称判断课程信息是否存在
	 * 
	 * @param courseName
	 */
	private Integer getCourseByCourseName(String courseName) {
		return dao.getCourseByCourseName(courseName);
	}

	public Boolean moderatorJudge(PersonInfo user, Integer courseId) {
		if (courseId == null) {
			throw new LogicException("课程ID为空");
		}
		Course course = new Course();
		course.setCourseId(courseId);
		List<Course> list = dao.queryCourseList(course);
		if (list == null) {
			throw new LogicException("课程信息为空");
		}
		Course result = list.get(0);
		if (result.getModerator() == null) {
			return false;
		} else {
			if (result.getModerator().getUserId() == user.getUserId()) {
				return true;
			} else {
				return false;
			}
		}
	}
}
