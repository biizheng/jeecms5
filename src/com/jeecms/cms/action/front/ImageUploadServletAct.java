package com.jeecms.cms.action.front;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.jeecms.cms.json.JsonResult;
import com.jeecms.common.upload.UploadUtils;
import com.jeecms.common.util.Msg;
import com.jeecms.common.web.ResponseUtils;

public class ImageUploadServletAct extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String WINDOW_FOLD_PATH = "/";
	private static final String VIDEO_EXT=".flv.avi.mov.wmv.mpg.rmvb.mp4";
	private static final String VIDEO_PATH=WINDOW_FOLD_PATH+"video";
	
	
	@SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        String savePath = this.getServletConfig().getServletContext().getRealPath("");  
        Msg msg = new Msg();
        savePath = savePath + this.WINDOW_FOLD_PATH+ "upload";
        String url = this.WINDOW_FOLD_PATH + "upload";
        
        
        File f1 = new File(savePath);  
        System.out.println(savePath);  
        if (!f1.exists()) {  
            f1.mkdirs();  
        }
        DiskFileItemFactory fac = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(fac);  
        upload.setHeaderEncoding("utf-8");  
        List fileList = null;  
        try {  
            fileList = upload.parseRequest(request);  
        } catch (FileUploadException ex) {  
            return;  
        }  
  
        Iterator<FileItem> it = fileList.iterator();  
        String name = "";  
        String extName = "";  
        while (it.hasNext()) {  
            FileItem item = it.next();  
            if (!item.isFormField()) {
                name = item.getName();  
                long size = item.getSize();  
                String type = item.getContentType();  
                System.out.println(size + " " + type);  
                if (name == null || name.trim().equals("")) {  
                    continue;  
                }  
  
                //扩展名格式：   
                if (name.lastIndexOf(".") >= 0) {
                    extName = FilenameUtils.getExtension(name);
                }

                File file = null;  
                do {
                    //生成文件名：  
                  //  name = UUID.randomUUID().toString();
                	String foldYM = UploadUtils.generateMonthname();
                	foldYM = savePath + File.separator + foldYM;
                	// upload\201501"
                	File fileYMDir = new File(foldYM);
                	//日期文件夹，没有则生成 
                	if(!fileYMDir.exists()){
                		fileYMDir.mkdir();
                	}
                	//生成新的文件名
                    savePath = UploadUtils.generateFilename(savePath, extName);
                    file = new File(savePath);
                    
                    int n = savePath.indexOf(this.WINDOW_FOLD_PATH+"upload");
                    url = savePath.substring(n);
                    msg.setUrl(url);
                    msg.setName(name);
                    msg.setSuccess(true);
                } while (file.exists());  
                File saveFile = new File(savePath);  
                try {  
                    item.write(saveFile);
                    String filename = FilenameUtils.getName(saveFile.getName());
                    String ext = FilenameUtils. getExtension(filename);

                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }
      
   //     response.getWriter().print(savePath);  
		String json_data = JsonResult.ajax(true, url);
		ResponseUtils.renderJson(response, json_data);
		
/*		JSONObject jsonObject = JSONObject.fromObject(msg);
		ResponseUtils.renderJson(response, jsonObject.toString());*/
    }  

}
