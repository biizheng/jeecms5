package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.Notarization;
import com.jeecms.common.page.Pagination;

public interface NotarizationMng {
	public Pagination getPage(Integer category, String title, Integer siteId, int pageNo, int pageSize);
	
	public Notarization findById(Integer id);
	
	public List<Notarization> getAllList(Integer siteId);
	
	public List<Notarization> getListByPosition(Integer siteId, Integer position);
	
	public Notarization save(Notarization bean);
	
	public Notarization update(Notarization bean);
	
	public Notarization deleteById(Integer id);
}
