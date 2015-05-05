package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.NotzResultDao;
import com.jeecms.cms.entity.main.NotzResult;
import com.jeecms.cms.manager.main.NotzResultMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class NotzResultMngImpl implements NotzResultMng{

	@Override
	public Pagination getPage(Integer state, Integer siteId, String result, int pageNo, int pageSize) {
		return dao.getPage(state, siteId, result, pageNo, pageSize);
	}

	@Override
	public NotzResult findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public NotzResult save(NotzResult bean) {
		return dao.save(bean);
	}

	@Override
	public NotzResult update(NotzResult bean) {
		Updater<NotzResult> updater = new Updater<NotzResult>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	@Override
	public NotzResult delete(Integer id) {
		return dao.delete(id);
	}

	private NotzResultDao dao;

	@Autowired
	public void setDao(NotzResultDao dao) {
		this.dao = dao;
	}
	
	
}
