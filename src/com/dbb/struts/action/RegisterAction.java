/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.dbb.struts.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.dbb.domain.Users;
import com.dbb.service.UserService;
import com.dbb.struts.form.UserForm;
import com.dbb.utils.MyTools;

/** 
 * MyEclipse Struts
 * Creation date: 05-27-2017
 * 
 * XDoclet definition:
 * @struts.action path="/register" name="userForm" scope="request"
 */
public class RegisterAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserForm userForm = (UserForm) form;// TODO Auto-generated method stub
		
		//ȡ��������Ϣ
		String name=userForm.getName();
		FormFile formFile=userForm.getMyphoto();
		
		//ԭ�����ļ���
		String filepath=formFile.getFileName();
		System.out.print(filepath);
		
		//�µ��ļ���
		String filenewname=null;
		
		InputStream is=null;
		OutputStream os=null;
		try {
			//�õ�������
			is=formFile.getInputStream();
			
			//�õ�files�ļ�����web�������е���ʵ��ַ�������ļ������λ��
			String keeppath=this.getServlet().getServletContext().getRealPath("/files");
			System.out.print(keeppath);
			
			filenewname=MyTools.getNewFileName(filepath);
			//ͨ�������ļ���ʵ�ʵ�ַ�����õ������
			os=new FileOutputStream(keeppath+"\\"+filenewname);
			
			//��ȡ�ļ���д��������
			int len=0;
			byte []bytes=new byte[1024];
			
			//ѭ����ȡ
			while((len=is.read(bytes))>0){
				
				//��һ�㣬дһ��
				os.write(bytes, 0, len);
			}
			
			//����ļ��ϴ��ɹ����Ͱ��û�����Ϣ�������ݿ���
			UserService userService=new UserService();
			Users user=new Users();
			user.setName(name);
			user.setFileName(filepath);
			user.setFileNewName(filenewname);
			
			if(!userService.addUser(user)){
				//���Ӵ���
				return mapping.findForward("err");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				os.close();
				is.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return mapping.findForward("registerOk");
	}
}