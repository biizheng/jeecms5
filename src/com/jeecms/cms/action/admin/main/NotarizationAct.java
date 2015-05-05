package com.jeecms.cms.action.admin.main;

import static com.jeecms.common.page.SimplePage.cpn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.Notarization;
import com.jeecms.cms.manager.main.NotarizationMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;

@Controller
public class NotarizationAct {

	@RequestMapping("/notarization/v_list.do")
	public String list(Integer category, String title, Integer pageNo, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Integer siteId = site.getId();
		Pagination pagination = manager.getPage(category, title, siteId, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("category", category);
		model.addAttribute("title", title);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		return "notarization/list";
	}

	@RequestMapping("/notarization/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<Notarization> notzList = manager.getAllList(site.getId());

		model.addAttribute("notzList", notzList);
		return "notarization/add";
	}

	@RequestMapping("/notarization/v_edit.do")
	public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<Notarization> notzList = manager.getAllList(site.getId());
		Notarization bean = manager.findById(id);
		notzList.remove(bean);
		
		model.addAttribute("notzList", notzList);
		model.addAttribute("bean", bean);
		model.addAttribute("pageNo", pageNo);
		return "notarization/edit";
	}

	@RequestMapping("/notarization/o_save.do")
	public String save(Notarization bean, Integer next_id, Integer preview_id, Integer[] nexts, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		bean.setSiteId(site.getId());
		if (next_id != null && next_id > 0) {
			Notarization next = manager.findById(next_id);
			bean.setNext(next);
		}
		if (preview_id != null && preview_id > 0) {
			Notarization preview = manager.findById(preview_id);
			bean.setPreview(preview);
		}
		if (nexts != null && nexts.length > 0) {
			for (int i = 0; i < nexts.length; i++) {
				if (nexts[i] != null && nexts[i] > 0) {
					Notarization n = manager.findById(nexts[i]);
					if (n != null) {
						if (i == 0)
							bean.setN1(n);
						else if (i == 1)
							bean.setN2(n);
						else if (i == 2)
							bean.setN3(n);
						else if (i == 3)
							bean.setN4(n);
						else if (i == 4)
							bean.setN5(n);
						else if (i == 5)
							bean.setN6(n);
						else if (i == 6)
							bean.setN7(n);
						else if (i == 7)
							bean.setN8(n);
					}
				}

			}
		}
		bean = manager.save(bean);
		return "redirect:v_list.do";
	}

	@RequestMapping("/notarization/o_update.do")
	public String update(Notarization bean, Integer next_id, Integer preview_id, Integer[] nexts, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		bean.setSiteId(site.getId());
		Notarization next = null;
		Notarization preview = null;
		
		if (next_id != null && next_id > 0) {
			next = manager.findById(next_id);
			bean.setNext(next);
		}else{
			bean.setNext(next);
		}
		if (preview_id != null && preview_id > 0) {
			preview = manager.findById(preview_id);
			bean.setPreview(preview);
		}else{
			bean.setPreview(preview);
		}
		
		if (nexts != null && nexts.length > 0) {
			bean.init();
			for (int i = 0; i < nexts.length; i++) {
				if (nexts[i] != null && nexts[i] > 0) {
					Notarization n = manager.findById(nexts[i]);
					if (n != null) {
						if (i == 0)
							bean.setN1(n);
						else if (i == 1)
							bean.setN2(n);
						else if (i == 2)
							bean.setN3(n);
						else if (i == 3)
							bean.setN4(n);
						else if (i == 4)
							bean.setN5(n);
						else if (i == 5)
							bean.setN6(n);
						else if (i == 6)
							bean.setN7(n);
						else if (i == 7)
							bean.setN8(n);
					}
				}

			}
		}
		bean = manager.update(bean);
		return "redirect:v_list.do";
	}
	
	@Autowired
	public NotarizationMng manager;

	public void setManager(NotarizationMng manager) {
		this.manager = manager;
	}

}
