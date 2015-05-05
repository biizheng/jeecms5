package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.NotarizationDao;
import com.jeecms.cms.entity.main.Notarization;
import com.jeecms.cms.manager.main.NotarizationMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class NotarizationMngImpl implements NotarizationMng{

	@Override
	public Pagination getPage(Integer category, String title, Integer siteId, int pageNo, int pageSize) {
		return dao.getPage(category, title, siteId, pageNo, pageSize);
	}

	@Override
	public Notarization findById(Integer id) {
		return dao.findById(id);
	}

	
	public List<Notarization> getAllList(Integer siteId){
		return dao.getAllList(siteId);
	}
	
	public List<Notarization> getListByPosition(Integer siteId, Integer position){
		return dao.getListByPosition(siteId, position);
	}
	
	@Override
	public Notarization save(Notarization bean) {
		return dao.save(bean);
	}

	public Notarization update(Notarization bean){
		Updater<Notarization> updater = new Updater<Notarization>(bean);
		updater.setUpdateMode(Updater.UpdateMode.MAX);
		bean = dao.updateByUpdater(updater);
		//bean = dao.update(bean);
		return bean;
	}
	
	@Override
	public Notarization deleteById(Integer id) {
		return dao.deleteById(id);
	}
	
	private NotarizationDao dao;

	@Autowired
	public void setDao(NotarizationDao dao) {
		this.dao = dao;
	}
	
}
