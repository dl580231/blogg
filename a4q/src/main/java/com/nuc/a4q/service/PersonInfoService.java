package com.nuc.a4q.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.a4q.dao.FloorDao;
import com.nuc.a4q.dao.PersonInfoDao;
import com.nuc.a4q.entity.Floor;
import com.nuc.a4q.entity.PageDivide;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.enums.ResultEnum;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.utils.PageUtil;

@Service
/* @Transactional */
public class PersonInfoService {
	@Autowired
	private PersonInfoDao dao;
	@Autowired
	private FloorDao floorDao;
	/**
	 * 用户信息注册
	 * 
	 * @param personInfo
	 * @throws Exception
	 */
	public void addPersoninfo(PersonInfo personInfo) {
		// 1.判断参数是否为空
		// 2.补充一些信息
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		// 3.操作数据库
		duplicateJudge(personInfo);// 字段重复判断
		dao.insertPersonInfo(personInfo);
	}

	/**
	 * 登陆认证
	 * 
	 * @param personInfo
	 * @return
	 * @throws Exception
	 */
	public PersonInfo loginAuth(PersonInfo personInfo) {
		if (personInfo == null || (personInfo.getPhone() == null && personInfo.getEmail() == null)) {
			throw new LogicException("用户名不能为空");
		}
		PersonInfo info = dao.queryPresonInfo(personInfo);
		if (info == null) {
			throw new LogicException("用户名不存在");
		} else {
			if (personInfo.getPassword().equals(info.getPassword())) {
				info.setPassword(null);
				return info;
			} else {
				throw new LogicException("用户名或密码错误");
			}
		}
	}

	/**
	 * 验证数据库中的email和phone是否重复
	 * 
	 * @param personInfo
	 */
	public void duplicateJudge(PersonInfo personInfo) {
		PersonInfo info = new PersonInfo();
		info.setEmail(personInfo.getEmail());
		PersonInfo resultEmail = dao.queryPresonInfo(info);
		if (resultEmail != null) {
			throw new LogicException("邮箱地址已经被注册");
		}
		info.setEmail(null);
		info.setPhone(personInfo.getPhone());
		PersonInfo resultPhone = dao.queryPresonInfo(info);
		if (resultPhone != null) {
			throw new LogicException(ResultEnum.DUPLICATE_PHONE.getState(), ResultEnum.DUPLICATE_PHONE.getStateInfo());
		}
	}

	/**
	 * 分页查询用户信息
	 * 
	 * @param pageDivide
	 * @param personInfo
	 */
	public PageDivide getPersonInfoList(PageDivide pageDivide, PersonInfo personInfo) {
		// 0.参数判空
		if (pageDivide == null)
			pageDivide = new PageDivide();
		// 1.操作数据库
		Integer rowCount = dao.queryPersonInfoCount(personInfo);
		if (rowCount < 1)
			throw new LogicException("查询数据为空");
		Integer rowStart = PageUtil.getRowStart(pageDivide.getCurrentPage(), pageDivide.getPageRowCount());
		if (rowStart > rowCount)
			throw new LogicException("查询数据为空");
		List<PersonInfo> personInfoList = dao.queryPersonInfoList(rowStart, pageDivide.getPageRowCount(), personInfo);
		// 2.封装pageDivide
		PageUtil.buildPageDivide(pageDivide, rowCount, pageDivide.getPageRowCount(), personInfoList);
		// 3.向controller返回数据
		return pageDivide;
	}

	public void removeUser(PersonInfo personInfo) {
		if (personInfo == null) {
			throw new LogicException("请增添删除信息");
		}
		dao.deleteUser(personInfo);
	}

	public PersonInfo getUserById(Integer userId) {
		if (userId == null) {
			throw new LogicException("查询user信息失败");
		}
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(userId);
		PersonInfo result = dao.queryPresonInfo(personInfo);
		if (result == null) {
			throw new LogicException("该用户不存在");
		}
		return result;
	}

	public void updateUser(PersonInfo personInfo) {
		personInfo.setLastEditTime(new Date());
		dao.updateUser(personInfo);
	}
	
	public Integer judgeNotice(PersonInfo user) {
		Floor floor = new Floor();
		floor.setUser(user);
		floor.setLookOver(0);
		List<Floor> list = floorDao.queryFloorList(floor);
		if(list.size() > 0) {
			return 1;
		}
		return 0;
	}
}
