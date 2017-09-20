package action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import util.ParamUtil;
import util.MessageKit;
import util.Page;
import util.StringUtil;
import dao.ConfigDao;
import vo.Config;



@Controller
@RequestMapping("/sys/config")
public class ConfigAction {
	
	private Logger log = LoggerFactory.getLogger(ConfigAction.class);		
	private ConfigDao configDao=ConfigDao.getInstance();
	
	@RequestMapping(value = "/list.do")
	public String list(HttpServletRequest request,HttpServletResponse response			
			,Model model) {	
		Integer id=ParamUtil.getIntegerParameter(request,"id");
		String configContent=ParamUtil.getStringParameter(request,"configContent");
		Integer configType=ParamUtil.getIntegerParameter(request,"configType");
		
		Map params = new HashMap();
		params.put("id", id);
		params.put("configContent", configContent);
		params.put("configType", configType);
			
		int pageNo=Page.getCurrentPage(request);
		int pageSize=Page.getPageSize(request,20);		
		model.addAttribute("list", configDao.find(params,pageNo,pageSize));			   
		Page.setPageBeans(configDao.findCount(params), pageSize, request, model);		
		return "config/list";
	 }	
	
	/**
	 * update vo详细信息显示页
	 * @param request
	 * @param response
	 * @param model
	 * @return	 
	 */
	@RequestMapping(value = "/update.do")
	public String update(HttpServletRequest request,HttpServletResponse response			
			,Model model) {	
		Integer id=ParamUtil.getIntegerParameter(request, "id");
		Config vo=configDao.findByPK(id);
		model.addAttribute("vo", vo);
		return "config/update";
	 }
	 
	 /**
	 * 提交更新，根据参数更新对象
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @author mengdz
	 */
	@RequestMapping(value = "/updatepost.do")	
	public void updatepost(HttpServletRequest request,HttpServletResponse response
			,Model model) {		
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		if(StringUtil.isNotNullorEmpty(ParamUtil.getStringParameter(request, "id_set"))){
			paramsMap.put("id_set", ParamUtil.getIntegerParameter(request, "id_set"));
		}
		if(StringUtil.isNotNullorEmpty(ParamUtil.getStringParameter(request, "configContent_set"))){
			paramsMap.put("configContent_set", ParamUtil.getStringParameter(request, "configContent_set"));
		}
		if(StringUtil.isNotNullorEmpty(ParamUtil.getStringParameter(request, "configType_set"))){
			paramsMap.put("configType_set", ParamUtil.getIntegerParameter(request, "configType_set"));
		}
		
		if(StringUtil.isNotNullorEmpty(ParamUtil.getStringParameter(request, "id"))){
			paramsMap.put("id", ParamUtil.getIntegerParameter(request, "id"));
		}
		if(StringUtil.isNotNullorEmpty(ParamUtil.getStringParameter(request, "configContent"))){
			paramsMap.put("configContent", ParamUtil.getStringParameter(request, "configContent"));
		}
		if(StringUtil.isNotNullorEmpty(ParamUtil.getStringParameter(request, "configType"))){
			paramsMap.put("configType", ParamUtil.getIntegerParameter(request, "configType"));
		}
		int effect=configDao.update(paramsMap);
		MessageKit.displayJsonResult(response, effect, null, null, null);		
	}
	 
	 /**
	 * update 提交
	 * @param request
	 * @param response
	 * @param model	 
	 */
	@RequestMapping(value = "/updatevopost.do")
	public void updatevopost(HttpServletRequest request,HttpServletResponse response			
			,Model model) {		
		Integer id=ParamUtil.getIntegerParameter(request,"id");
		String configContent=ParamUtil.getStringParameter(request,"configContent");
		Integer configType=ParamUtil.getIntegerParameter(request,"configType");
			
		Config vo=new Config();
		vo.setId(id);
		vo.setConfigContent(configContent);
		vo.setConfigType(configType);
				
		int effect=configDao.update(vo);
		MessageKit.displayJsonResult(response, effect, null, null, null);
	 }	
	
	/**
	 * add vo详细信息显示页
	 * @param request
	 * @param response
	 * @param model
	 * @return	
	 */
	@RequestMapping(value = "/add.do")
	public String add(HttpServletRequest request,HttpServletResponse response			
			,Model model) {			
		return "config/add";
	 }
	 
	 /**
	 * add 提交
	 * @param request
	 * @param response
	 * @param model	 
	 */
	@RequestMapping(value = "/addpost.do")
	public void addpost(HttpServletRequest request,HttpServletResponse response			
			,Model model) {	
		Integer id=ParamUtil.getIntegerParameter(request,"id");
		String configContent=ParamUtil.getStringParameter(request,"configContent");
		Integer configType=ParamUtil.getIntegerParameter(request,"configType");
			
		Config vo=new Config();
		vo.setId(id);
		vo.setConfigContent(configContent);
		vo.setConfigType(configType);
				
		int effect=configDao.insert(vo);
		MessageKit.displayJsonResult(response, effect, null, null, null);
	 }	 
	/**
	 * 批量删除 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/delete.do")
	public void delete(HttpServletRequest request,HttpServletResponse response			
			,Model model) {	
		String[] ids=request.getParameterValues("ids[]");
		int effect=configDao.delete(ids);
		MessageKit.displayJsonResult(response, effect, null, null, null);
	 }
}
