package com.nuc.a4q.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.a4q.dao.BlogDao;
import com.nuc.a4q.dao.BlogHistoryDao;
import com.nuc.a4q.dao.CourseDao;
import com.nuc.a4q.dao.PersonInfoDao;
import com.nuc.a4q.dto.BlogDto;
import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.BlogHistory;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.UserRank;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.recommend.ItemCF;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private PersonInfoDao userDao;
	@Autowired
	private BlogHistoryDao historyDao;
	@Autowired
	private ItemCF cf;
	
	public Integer addBlog(Blog blog,PersonInfo user) {
		blog.setUserId(user.getUserId());
		blog.setCreateTime(new Date());
		blog.setLastEditTime(new Date());
		blogDao.insert(blog);
		return blog.getBlogId();
	}
	
	public BlogDto getBlogById(Integer blogId) {
		if(blogId == null)
			throw new LogicException("blogId为空");
		Blog blog = blogDao.selectByPrimaryKey(blogId);
		Course course = new Course();
		course.setCourseId(blog.getCourseId());
		course = courseDao.queryCourseList(course).get(0);
		PersonInfo user = new PersonInfo();
		user.setUserId(blog.getUserId());
		user = userDao.queryPresonInfo(user);
		BlogDto blogDto = new BlogDto();
		blogDto.setBlog(blog);
		blogDto.setCourse(course);
		blogDto.setUser(user);
		return blogDto;
	}
	
	public List<Blog> getBlogList(Blog blog) {
		if(blog == null)
			throw new LogicException("查询数据为空");
		List<Blog> list = blogDao.getBlogList(blog);
		Collections.sort(list, Comparator.comparing(Blog::getCreateTime));
		Collections.reverse(list);
		return list;
	}
	
	

	public void readCountAdd(Integer blogId) {
		blogDao.readCountAdd(blogId);
	}

	public void logicRm(Integer blogId,PersonInfo user) {
		if(blogId == null)
			throw new LogicException("查询为空");
		Blog blog = blogDao.selectByPrimaryKey(blogId);
		if(blog!=null) {
			if(user.getUserId()!=null&&user.getUserId().equals(blog.getUserId())) {
				Blog blogUpdate = new Blog();
				blogUpdate.setBlogId(blogId);
				blogUpdate.setEnableView(0);
				blogDao.updateByPrimaryKeySelective(blogUpdate);
				return;
			}
		}
		throw new LogicException("无权操作");
	}

	public List<UserRank> getUserRank() {
		List<UserRank> userList = blogDao.getUserRank();
		Collections.sort(userList, Comparator.comparing(UserRank::getNum));
		Collections.reverse(userList);
		return userList;
	}

	/**
	 * 获得阅读量排行
	 * @return
	 */
	public List<Blog> getBlogRankByReadCount(){
		List<Blog> list = blogDao.getBlogRankByReadCount();
		Collections.sort(list, Comparator.comparing(Blog::getReadCount));
		Collections.reverse(list);
		return list;
	}

	public HashMap<Object, Object> getBlogOrderByPriority(Integer rowStart,Integer rowSize,Integer courseId) {
		if(rowStart==null || rowSize==null)
			throw new LogicException("查询参数错误");
		Integer count = blogDao.queryBlogCount(courseId);//这里应该设置上参数
		Blog blog = new Blog();
		blog.setCourseId(courseId);
		List<BlogDto> list = blogDao.getBlogOrderByPriority(rowStart,rowSize,blog);
		HashMap<Object, Object> map = new HashMap<Object,Object>();
		map.put("list", list);
		map.put("count", count);
		return map;
	}

	public List<BlogDto> getRecommendBlog(Integer userId) throws TasteException{
		BlogHistory history = new BlogHistory();
		history.setUserId(userId);
		List<BlogHistory> historyDaoList = historyDao.getBlogHistory(history);
		List<Integer> blogIdList = new ArrayList<Integer>();
		if(historyDaoList.size() == 0) {
			/*------基于人口统计学的推荐算法解决冷启动问题开始-----*/
			PersonInfo requestUser = new PersonInfo();
			requestUser.setUserId(userId);
			PersonInfo user = userDao.queryPresonInfo(requestUser);
			requestUser.setUserId(null);
			requestUser.setGender(user.getGender());
			requestUser.setUserType(user.getUserType());
			List<Integer> userIdlist = historyDao.getUserIdInHistory(requestUser);
			for(Integer userIdP : userIdlist) {
				blogIdList = cf.getRecommengBasedItemCF(userIdP,"tb_blog_history","blog_id");
				if(blogIdList.size()>0) {
					break;
				}
			}
			if(blogIdList.size() == 0)
				return null;
			/*------基于人口统计学的推荐算法解决冷启动问题结束-----*/
		}else {
			blogIdList = cf.getRecommengBasedItemCF(userId,"tb_blog_history","blog_id");
		}
		LinkedList<BlogDto> blogList = new LinkedList<BlogDto>();
		for(Integer blogId : blogIdList) {
			blogList.add(getBlogById(blogId));
		}
		return blogList;
	}

	public List<BlogDto> getSerachBlog(String blogContent, String blogTitle) {
		Blog blog = new Blog();
		blog.setBlogContent(blogContent);
		blog.setBlogTitle(blogTitle);
		List<BlogDto> list = blogDao.getBlogOrderByPriority(null, null, blog);
		return list;
	}

	public void editBlog(Blog blog,PersonInfo user) {
		if(judgeBelong(blog, user)) {
			blogDao.updateByPrimaryKeySelective(blog);
		}else {
			throw new LogicException("无权操作");
		}
	}
	
	public boolean judgeBelong(Blog blog,PersonInfo user) {
		Blog blogJ = blogDao.selectByPrimaryKey(blog.getBlogId());
		if(blogJ.getUserId().equals(user.getUserId()))
			return true;
		return false;
	}
}
