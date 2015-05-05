package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_GONGZHENG;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.entity.assist.CmsAdvertising;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.Notarization;
import com.jeecms.cms.entity.main.NotzResult;
import com.jeecms.cms.manager.main.NotarizationMng;
import com.jeecms.cms.manager.main.NotzResultMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.web.ResponseUtils;

@Controller
public class GongZhengAct {
	public static final String TPL_AD = "gongzheng";

	
	@RequestMapping(value = "/gongzheng.htm")
	public String index(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Notarization bean = null;
		if (id != null) {
			bean = notzMng.findById(id);
		}else{
			List<Notarization> notzs = notzMng.getListByPosition(site.getId(), Notarization.POSITON_FIRST);
			if(notzs != null && notzs.size()>0){
				bean = notzs.get(0);
			}
		}
		FrontUtils.frontData(request, model, site);
		
		model.addAttribute("bean", bean);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_GONGZHENG, TPL_AD);
	}
	
	@RequestMapping(value = "/gongzheng/result.htm", method=RequestMethod.POST)
	public String submit(NotzResult bean, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		bean.setCreateDate(new Date());
		bean.setSiteId(site.getId());
		bean.setStatus(bean.STATE_NOLOOK);
		bean = notzResultMng.save(bean);

		FrontUtils.frontData(request, model, site);
		
		model.addAttribute("bean", bean);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_GONGZHENG, TPL_AD);
	}
	@RequestMapping(value = "/getNextGongZheng.htm")
	public void getGongZheng(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Notarization bean = null;
		if (id != null) {
			bean = notzMng.findById(id);
		}else{
			List<Notarization> notzs = notzMng.getListByPosition(site.getId(), Notarization.POSITON_FIRST);
			if(notzs != null && notzs.size()>0){
				bean = notzs.get(0);
			}
		}
		FrontUtils.frontData(request, model, site);
		
		
		 Map<String, Object> result = new HashMap<String, Object>();
         result.put( "data", bean);

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false); //设置默认忽略
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 
		jsonConfig.setExcludes( new String[]{ "preview", "next"} ) ;
		
		JSONObject jsonObject = JSONObject.fromObject(result, jsonConfig);
		
/*		JSONObject jsonObject= new JSONObject();
		jsonObject.element("data", bean);*/
		ResponseUtils.renderJson(response, jsonObject.toString());
	}
	
	@Autowired
	private NotarizationMng notzMng;
	@Autowired
	private NotzResultMng notzResultMng;
}
