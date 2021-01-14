package com.packtpub.restapp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.packtpub.model.User;
import com.packtpub.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService; 
	
	@ResponseBody
	@RequestMapping("")
	public List<User> getAllUsers() {		
		return userService.getAllUsers();
	}
	
	@ResponseBody
	@RequestMapping("/{id}")
	public User getUser(@PathVariable("id") Integer id) {
		return userService.getUser(id); 		
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> createUser(
			@RequestParam(value = "userid") Integer userid, 
			@RequestParam(value = "username") String username) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		userService.createUser(userid, username);		
		map.put("result", "added");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Map<String, Object> updateUser(
			@RequestParam(value = "userid") Integer userid,
			@RequestParam(value = "username") String username) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		userService.updateUser(userid, username);
		map.put("result", "Updated");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteUser(
			@PathVariable("id") Integer userid) {
		userService.deleteUser(userid);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("result", "deleted");
		return map;
	}

}
