package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.NotarizationDao;
import com.jeecms.cms.entity.main.Notarization;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class NotarizationDaoImpl extends HibernateBaseDao<Notarization, Integer> implements NotarizationDao{

	@Override
	public Pagination getPage(Integer category, String title, Integer siteId, int pageNo, int pageSize) {
		Finder f = Finder.create("from Notarization bean where 1=1");
		if(siteId != null && siteId>0){
			f.append(" and bean.siteId=:siteId");
			f.setParam("siteId", siteId);
		}
		if(category != null && category>0){
			f.append(" and bean.category=:category");
			f.setParam("category", category);
		}
		if(!StringUtils.isBlank(title)){
			f.append(" and bean.title like '%"+title+"%'");
		}
		
		f.append(" order by bean.priority asc, bean.id desc");
		
		return find(f, pageNo, pageSize);
	}

	public List<Notarization> getAllList(Integer siteId){
		Finder f= getFinder(siteId, null);
		f.setCacheable(true);
		return find(f);
	}
	
	public List<Notarization> getListByPosition(Integer siteId, Integer position){
		Finder f= getFinder(siteId, position);
		f.setCacheable(true);
		return find(f);
	}
	
	public Finder getFinder(Integer siteId, Integer position){
		Finder f = Finder.create("from Notarization bean where 1=1");
		if(siteId != null && siteId>0){
			f.append(" and bean.siteId=:siteId");
			f.setParam("siteId", siteId);
		}
		if(position != null && position>0){
			f.append(" and bean.position=:position");
			f.setParam("position", position);
		}
		f.append(" order by bean.priority asc, bean.id desc");
		return f;
	}
	@Override
	public Notarization findById(Integer id) {
		Notarization bean = get(id);
		return bean;
	}

	@Override
	public Notarization save(Notarization bean) {
		getSession().save(bean);
		return bean;
	}

	public Notarization update(Notarization bean){
		getSession().update(bean);
		return bean;
	}
	@Override
	public Notarization deleteById(Integer id) {
		Notarization bean = get(id);
		if(bean != null){
			getSession().delete(bean);
		}
		return bean;
	}

	@Override
	protected Class<Notarization> getEntityClass() {
		return Notarization.class;
	}

}
