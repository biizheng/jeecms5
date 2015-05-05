package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.NotzResult;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface NotzResultMng {
	public Pagination getPage(Integer state, Integer siteId, String result, int pageNo, int pageSize);
	
	public NotzResult findById(Integer id);
	
	public NotzResult save(NotzResult bean);
	
	public NotzResult update(NotzResult bean);
	
	public NotzResult delete(Integer id);
}
