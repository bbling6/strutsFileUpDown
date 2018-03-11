package com.dbb.service;

import java.util.ArrayList;

import com.dbb.domain.Users;
import com.dbb.utils.SqlHelper;

public class UserService {
	
	//返回一个用户
	public Users getUser(String name){
		
		String sql="select * from fileusers where name=?";
	
		String[] para={name};
		ArrayList al=SqlHelper.executeQuery2(sql, para);
		
		Object[]objects=(Object[]) al.get(0);
		Users users=new Users();
		
		users.setName(objects[0].toString());
		users.setFileName(objects[1].toString());
		users.setFileNewName(objects[2].toString());
		
		return users;
	}
	
	//添加用户
	public boolean addUser(Users user){
		boolean b=true;
		String sql="insert into fileusers values(?,?,?)";
		String []paras={user.getName(),user.getFileName(),user.getFileNewName()};
		
		try {
			SqlHelper.executeUpdata(sql, paras);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			b=false;
		}
		return b;
	}
	
	//得到所有的用户
	public ArrayList getUserList(){
		String sql="select * from fileusers";
		
		
		ArrayList al=SqlHelper.executeQuery2(sql, null);
		ArrayList<Users> aList=new ArrayList<Users>();
		
		//进行二次封装
		for (int i = 0; i < al.size(); i++) {
			Users users=new Users();
			Object[]objects=(Object[]) al.get(i);
			
			users.setName(objects[0].toString());
			users.setFileName(objects[1].toString());
			users.setFileNewName(objects[2].toString());
			
			aList.add(users);
			
		}
		
		return aList;
	}
	
	

}
