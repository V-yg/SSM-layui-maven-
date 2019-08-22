package com.yigang.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yigang.entity.Admin;
import com.yigang.entity.AdminLog;
import com.yigang.entity.Menu;
import com.yigang.entity.Role;
import com.yigang.service.AdminService;
import com.yigang.utils.GsonUtil;
import com.yigang.utils.MD5Utils;
import com.yigang.utils.ResultUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	/************************************登录相关************************************/
	/*
	 * 处理管理员登录请求
	*/
	@RequestMapping("/login")
	@ResponseBody
	public ResultUtil login(String username, String password, HttpServletRequest request,HttpSession session)
			throws ParseException {
		Admin admin = adminService.login(username, password);
		if (null != admin) {
			session.setAttribute("admin", admin);
			String loginIp = request.getHeader("x-forwarded-for");
			if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
				loginIp = request.getHeader("Proxy-Client-IP");
			}
			if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
				loginIp = request.getHeader("WL-Proxy-Client-IP");
			}
			if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
				loginIp = request.getRemoteAddr();
			}
			Date date = new Date();// 获得系统时间.
			SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
			String nowTime = sdf.format(date);
			Date loginTime = sdf.parse(nowTime);
			adminService.insAdminLog(username, loginIp, loginTime);
			return ResultUtil.ok(admin.getId());
		} else {
			return ResultUtil.error();
		}
	}
	
	/************************************Admin相关************************************/
	/*
	 * 更新管理员
	 */
	@RequestMapping("/updateAdmin")
	@ResponseBody
	public ResultUtil updateAdmin(Admin admin) {
		try {
			adminService.updAdmin(admin);
			return ResultUtil.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error();
		}
	}
	/*
	 * 更新管理员
	 */
	@RequestMapping("/updAdmin")
	@ResponseBody
	public ResultUtil updAdmin(Admin admin) {
		if(admin!=null&&admin.getId()==1){
			return ResultUtil.error("不允许修改!");
		}
		try {
			adminService.updAdmin(admin);
			return ResultUtil.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error();
		}
	}
	
	/*
	 * 修改管理员登录密码
	 */
	@RequestMapping("changeAdminPassword")
	@ResponseBody
	public ResultUtil changeAdminPassword(String oldPassword, String newPassword1, String username) {
		Admin admin = adminService.getAdminByUsername(username);
		if (admin != null) {
			if (admin.getPassword().equals(MD5Utils.md5(oldPassword))) {
				admin.setPassword(MD5Utils.md5(newPassword1));
				adminService.updAdmin(admin);
				return ResultUtil.ok();
			} else {
				return new ResultUtil(501, "旧密码错误，请重新填写！");
			}
		}
		return new ResultUtil(500, "请求错误！");
	}
	
	/*
	 * 获取所有管理员列表，带分页
	 */
	@RequestMapping("/getAdminList")
	@ResponseBody
	public ResultUtil getAdminList(Integer page,Integer limit) {
		ResultUtil admins = adminService.getAdminsList(page, limit);
		return admins;
	}

	/*
	 * 检查管理员登录名是否相同，避免重复
	 */
	@RequestMapping("/checkAdminName/{username}")
	@ResponseBody
	public ResultUtil checkAdminName(@PathVariable("username")String username) {
		Admin admin = adminService.getAdminByUsername(username);
		if(admin!=null){
			return new ResultUtil(500,"管理员已存在！");
		}
		return new ResultUtil(0);
	}

	/*
	 * 编辑管理员
	 */
	@RequestMapping("/editAdmin/{id}")
	public String editAdmin(HttpServletRequest req,@PathVariable("id")int id) {
		Admin ad = adminService.getAdminById(id);
		List<Role> roles = adminService.getRoles();
		req.setAttribute("ad",ad);
		req.setAttribute("roles", roles);
		return "jsp/admin/editAdmin";
	}
	
	/*
	 * 查看管理员个人信息
	 */
	@RequestMapping("/personalDate")
	public String personalDate(HttpSession session) {
		Admin mAdmin = (Admin)session.getAttribute("admin");
		Admin admin = adminService.getAdminById(mAdmin.getId());
		session.setAttribute("admin1", admin);
		return "jsp/admin/personalInfo";
	}
	
	/*
	 * 增加管理员
	 */
	@RequestMapping("/insAdmin")
	@ResponseBody
	public ResultUtil insAdmin(Admin admin) {
		//防止浏览器提交
		Admin a = adminService.getAdminByUsername(admin.getUsername());
		if(a!=null){
			return new ResultUtil(500, "用户名已存在,请重试！");
		}
		adminService.insAdmin(admin);
		return ResultUtil.ok();
	}
	
	/*
	 * 通过Id删除管理员
	 */
	@RequestMapping("/delAdminById/{id}")
	@ResponseBody
	public ResultUtil delAdminById(@PathVariable("id")Long id) {
		if(id==1){
			return ResultUtil.error();
		}
		try {
			adminService.delAdminById(id);
			return ResultUtil.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error();
		}
	}
	
	/************************************AdminLog相关************************************/
	/*
	 * 获取所有的管理员登录日志
	 */
	@RequestMapping("getAdminLogList")
	@ResponseBody
	public ResultUtil getAdminLogList(Integer page, Integer limit) {
		return adminService.getAdminLogsList(page, limit);
	}
	
	/************************************Role相关************************************/
	/*
	 * 获取所有角色列表，带分页
	 */
	@RequestMapping("/getRoleList")
	@ResponseBody
	public ResultUtil getRoleList(Integer page,Integer limit) {
		return adminService.getRoles(page, limit);
	}
	
	/*
	 * 增加角色
	 */
	@RequestMapping("/insRole")
	@ResponseBody
	public ResultUtil insertRole(Role role,String m) {
		Role r = adminService.getRoleByRoleName(role.getRoleName());
		if(r!=null){
			return new ResultUtil(500, "角色名已存在,请重试！");
		}
		//角色信息保存
		adminService.insRole(role, m);
		return ResultUtil.ok();
	}
	
	/*
	 * 检查角色名是否重复
	 */
	@RequestMapping("/checkRoleName")
	@ResponseBody
	public ResultUtil checkRoleName(String roleName,Long roleId) {
		Role role = adminService.getRoleByRoleName(roleName);
		if(role==null){
			return new ResultUtil(0);
		}else if(role.getRoleId()==roleId){
			return new ResultUtil(0);
		}else{
			return new ResultUtil(500,"角色名已存在！");
		}
	}
	
	/*
	 * 更新角色
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public void updRole(Role role,String m) {
		//角色信息保存
		adminService.updRole(role, m);
	}
	
	/*
	 * 删除角色
	 */
	@RequestMapping("/delRole/{roleId}")
	@ResponseBody
	public ResultUtil delRole(@PathVariable("roleId")Long roleId) {
		ResultUtil resultUtil=new ResultUtil();
		try {
			adminService.delRole(roleId);
			resultUtil.setCode(0);
		} catch (Exception e) {
			resultUtil.setCode(500);
			e.printStackTrace();
		}
		return resultUtil;
	}
	
	/*
	 * 得到指定角色权限树
	 */
	@RequestMapping(value="/xtreedata",produces = {"text/json;charset=UTF-8"})
	@ResponseBody
	public String xtreeData(@RequestParam(value="roleId", defaultValue="-1") Long roleId) {
		Admin admin = new Admin();
		admin.setRoleId(roleId);
		return GsonUtil.entityToJson(adminService.getXtreeData(admin));
	}
	
	/************************************Menu相关************************************/
	/*
	 * 获取所有菜单
	 */
	@RequestMapping("/menuData")
	@ResponseBody
	public ResultUtil menuData(){
		List<Menu> list=adminService.getAllMenus();
		ResultUtil resultUtil=new ResultUtil();
		resultUtil.setCode(0);
		resultUtil.setCount(list.size()+0L);
		resultUtil.setData(list);
		return resultUtil;
	}
	
	/*
	 * 获取管理员对应的菜单，用于加载后台首页
	 */
	@RequestMapping("/getMenus")
	@ResponseBody
	public List<Menu> getMenus(HttpSession session){
		Admin mAdmin = (Admin)session.getAttribute("admin");
		Admin admin = adminService.getAdminById(mAdmin.getId());
		List<Menu> menus = null;
		if(null != admin) {
			menus = adminService.getMenus(admin);
		}
		return menus;
	}
	
	/*
	 * 通过Id更新菜单排序
	 */
	@RequestMapping("/updMenuSortingById")
	@ResponseBody
	public ResultUtil updMenuSortingById(Long menuId,String sorting){
		Menu menu = new Menu();
		menu.setMenuId(menuId);
		try{
		    Long.parseLong(sorting);
		}catch(NumberFormatException e)
		{
			return ResultUtil.error("修改失败，只允许输入整数！");
		}
		menu.setSorting(Long.parseLong(sorting));
		adminService.updMenuSortingById(menu);
		return ResultUtil.ok();
	}
	
	/*
	 * 点击新增菜单后的操作，跳转页面
	 */
	@RequestMapping("/toSaveMenu/{menuId}")
	public String toSaveMenu(@PathVariable("menuId") Long menuId,Model model){
		if(menuId!=null&&menuId!=1){
			Menu menus = new Menu();
			menus.setMenuId(menuId);
			model.addAttribute("menu",menus);
			model.addAttribute("flag","1");
			return "jsp/menu/menuForm";
		}else{
			model.addAttribute("msg","不允许操作！");
			return "jsp/active";
		}
	}
	
	/*
	 * 点击编辑菜单后的操作，跳转页面
	 */
	@RequestMapping("/toEditMenu/{menuId}")
	public String toEditMenu(@PathVariable("menuId") Long menuId,Model model){
		if(menuId!=null&&menuId!=1){
			Menu menus=adminService.getMenuById(menuId);
			model.addAttribute("menu",menus);
			return "jsp/menu/menuForm";
		}else if(menuId==1){
			model.addAttribute("msg","不允许操作此菜单！");
			return "jsp/active";
		}else{
			model.addAttribute("msg","不允许操作！");
			return "jsp/active";
		}
	}
	
	/*
	 * 根据id删除菜单
	 */
	@RequestMapping("/delMenuById/{menuId}")
	@ResponseBody
	public ResultUtil delMenuById(@PathVariable("menuId")Long menuId) {
		try {
			if(menuId==1){
				return ResultUtil.error("此菜单不允许删除！");
			}
			//查询是否有子菜单，不允许删除
			List<Menu> data=adminService.getMenusByParentId(menuId);
			if(data!=null&&data.size()>0){
				return ResultUtil.error("包含子菜单，不允许删除！");
			}
			adminService.delMenuById(menuId);
			return ResultUtil.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error("系统错误！");
		}
	}
	
	/*
	 * 维护菜单信息
	 */
	@RequestMapping("/menuForm")
	@ResponseBody
	public ResultUtil menuForm(Menu menus,String flag){
		if(StringUtils.isBlank(flag)){
			//同级菜单名不相同
			List<Menu> data=adminService.checkNameSameLevel(menus);
			Menu m = adminService.getMenuById(menus.getMenuId());
			Boolean f=false;
			if(m.getName().equals(menus.getName())||data.size()==0){
				f=true;
			}
			if(!f||data.size()>1){
				return ResultUtil.error("同级菜单名不能相同！");
			}
			menus.setSpread("false");
			adminService.updMenu(menus);
			return ResultUtil.ok("修改成功！");
		}else if(menus.getMenuId()!=1){
			menus.setParentId(menus.getMenuId());
			
			//规定只能3级菜单
			Menu m=adminService.getMenuById(menus.getMenuId());
			if(m!=null&&m.getParentId()!=0){
				Menu m1=adminService.getMenuById(m.getParentId());
				if(m1!=null&&m1.getParentId()!=0){
					return ResultUtil.error("此菜单不允许添加子菜单！");
				}
			}
			
			//同级菜单名不相同
			List<Menu> data=adminService.checkNameSameLevel(menus);
			if(data.size()>0){
				return ResultUtil.error("同级菜单名不能相同！");
			}
			
			menus.setMenuId(null);
			menus.setSpread("false");
			adminService.insMenu(menus);
			return ResultUtil.ok("添加成功！");
		}else{
			return ResultUtil.error("此菜单不允许操作！");
		}
	}
	
	/************************************页面跳转相关************************************/
	@RequestMapping("/index")
	public String index(HttpSession session) {
		return "redirect:/jsp/index.jsp";
	}
	
	@RequestMapping("/changePassword")
	public String changePassword() {
		return "jsp/admin/changePassword";
	}
	
	@RequestMapping("/main")
	public String getMain() {
		return "jsp/main";
	}
	
	@RequestMapping("/adminLoginLog")
	public String adminLoginLog() {
		return "jsp/admin/adminLogList";
	}
	
	@RequestMapping("/roleList")
	public String roleList() {
		return "jsp/role/roleList";
	}
	
	@RequestMapping("/editRole")
	public String editRole(Role role,Model model) {
		role=adminService.getRoleById(role.getRoleId());
		model.addAttribute("role", role);
		return "jsp/role/editRole";
	}
	
	@RequestMapping("/addRole")
	public String addRole() {
		return "jsp/role/addRole";
	}
	
	@RequestMapping("/addAdmin")
	public String addAdmin(HttpSession session){
		List<Role> roles = adminService.getRoles();
		session.setAttribute("roles", roles);
		return "jsp/admin/addAdmin";
	}
	
	@RequestMapping("/adminList")
	public String adminList(){
		return "jsp/admin/adminList";
	}
	
	@RequestMapping("/menuList")
	public String menuList() {
		return "jsp/menu/menuList";
	}
	
}
