package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Notarization;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface NotarizationDao {
	public Pagination getPage(Integer category, String title, Integer siteId, int pageNo, int pageSize);
	
	public Notarization findById(Integer id);
	
	public Notarization save(Notarization bean);
	
	public Notarization updateByUpdater(Updater<Notarization> updater);
	
	public Notarization update(Notarization bean);
	
	public List<Notarization> getAllList(Integer siteId);
	
	public List<Notarization> getListByPosition(Integer siteId, Integer position);
	
	public Notarization deleteById(Integer id);
}
