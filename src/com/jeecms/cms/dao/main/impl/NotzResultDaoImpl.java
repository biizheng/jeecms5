package com.jeecms.cms.dao.main.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.NotzResultDao;
import com.jeecms.cms.entity.main.NotzResult;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Repository
public class NotzResultDaoImpl extends HibernateBaseDao<NotzResult, Integer> implements NotzResultDao {

	@Override
	public Pagination getPage(Integer state, Integer siteId, String result, int pageNo, int pageSize) {
		Finder f = getFinder(state, siteId, result);
		return find(f, pageNo, pageSize);
	}

	private Finder getFinder(Integer state, Integer siteId, String result){
		Finder f = Finder.create(" from NotzResult bean where 1=1");
		
		if(!StringUtils.isBlank(result)){
			f.append(" and bean.result like '%"+result+"%'");
		}
		if(state != null && state>-1){
			f.append(" and bean.state=:state");
			f.setParam("state", state);
		}
		if(siteId != null && siteId>0){
			f.append(" and bean.siteId=:siteId");
			f.setParam("siteId", siteId);
		}
		f.append(" order by bean.id desc");
		return f;
	}
	
	@Override
	public NotzResult findById(Integer id) {
		NotzResult bean = get(id);
		return bean;
	}

	@Override
	public NotzResult save(NotzResult bean) {
		getSession().save(bean);
		return bean;
	}


	@Override
	public NotzResult delete(Integer id) {
		NotzResult bean = get(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	protected Class<NotzResult> getEntityClass() {
		return NotzResult.class;
	}

}
