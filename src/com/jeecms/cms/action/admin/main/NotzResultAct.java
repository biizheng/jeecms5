package com.jeecms.cms.action.admin.main;

import static com.jeecms.common.page.SimplePage.cpn;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.entity.main.NotzResult;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.manager.main.NotzResultMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;

@Controller
public class NotzResultAct {
	
	@RequestMapping(value="/notz_result/v_list.do")
	public String list(Integer state, String result,Integer pageNo, HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		Integer siteId = site.getId();
		Pagination pagination = manager.getPage(state,siteId,result, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		return "notz_result/list";
	}
	
	
	@RequestMapping(value="/notz_result/v_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model){
		NotzResult bean = manager.findById(id);
		//只更改状态
		bean.setStatus(NotzResult.STATE_LOOKED);
		manager.update(bean);
		model.addAttribute("bean", bean);
		return "notz_result/edit";
	}
	
	@RequestMapping(value="/notz_result/o_update.do")
	public String update(Integer id, Integer status, HttpServletRequest request, ModelMap model){
		NotzResult bean = manager.findById(id);
		//只更改状态
		bean.setStatus(status);
		manager.update(bean);
		return "redirect:v_list.do";
	}
	
	
	@RequestMapping(value="/notz_result/o_delete.do")
	public String delete(Integer id,  HttpServletRequest request, ModelMap model){
		manager.delete(id);
		return "redirect:v_list.do";
	}
	
	@Autowired
	private NotzResultMng manager;

	public void setManager(NotzResultMng manager) {
		this.manager = manager;
	}
	
}
