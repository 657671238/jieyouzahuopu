package com.gemptc.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.gemptc.dao.LunBoDao;
import com.gemptc.dao.LunBoDaoImp;
import com.gemptc.entity.LunBo;
import com.gemptc.util.JSONResult;
import com.gemptc.util.StringUUID;

/**
 * Servlet implementation class UpdateProductFileServlet
 */
@WebServlet("/addLunBo")
public class AddLunBoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLunBoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		JSONResult.JSONReturnWithData("1", "采用POST提交才可以上传数据", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//在POST获取上传的图片数据
		response.setContentType("text/html;charset=UTF-8");
		if(ServletFileUpload.isMultipartContent(request)){
			Map<String,Object> paraMap = new HashMap<String,Object>();
			DiskFileItemFactory cache = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(cache);
			upload.setHeaderEncoding("UTF-8");  //设置文件名的编码为UTF-8
			String fileFullPath = null;
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = upload.parseRequest(request);
				for(FileItem item:fileItems) {
					if(item.isFormField()) { //普通表单项
						paraMap.put(item.getFieldName(), item.getString("UTF-8"));
						System.out.println(item.getFieldName());
					}else {
						//这个就是文件了  处理文件的二进制流
						InputStream inStream = item.getInputStream();
						//判断是否存在二进制  是否存在图片的二进制
						if(inStream.available()>0) {
							//将流 写入到磁盘文件中
							//定义文件名称  UUID +时间
							String tempFileName = new SimpleDateFormat("yyyyMM_dd_HH_mm_ss_sss").format(new Date());
							String fileName = tempFileName+StringUUID.getUUID();
							//后缀名称
							String MIME = item.getContentType();
							int index = MIME.lastIndexOf("/");
							if(index!=-1) {
								fileName +="."+MIME.substring(index+1);
							}
							//写磁盘文件
							String path = StringUUID.getUploadDir() + "roll\\";
							fileFullPath = path + fileName;
							OutputStream outStream = new FileOutputStream(fileFullPath);
							IOUtils.copy(inStream, outStream);
							inStream.close();
							outStream.close();
							item.delete();
							//放入新的文件名
							paraMap.put("lb_image", fileName);
						}else {
							JSONResult.JSONReturnWithData("5", "图片不能为空", response);
							return;
						}
					}
				}
				//将Map转换为LunBO
				LunBo lb = new LunBo();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				BeanUtils.populate(lb, paraMap);
				//更新数据库
				System.out.println(lb);
				LunBoDao dao = new LunBoDaoImp();
				//开始插入数据
				if(dao.addLunBo(lb)) {
					response.sendRedirect(request.getContextPath()+"/admin/lunbolist.html");		
				}else {
					JSONResult.JSONReturnWithData("4", "数据添加失败", response);
				}				
			} catch (Exception e) {
				// 异常时删除已生成的图片
				if(fileFullPath!=null) {
					File file = new File(fileFullPath);
					if(file.exists()) {
						file.delete();
					}
				}
				e.printStackTrace();
				JSONResult.JSONReturnWithData("3", "解析表单项异常", response);
			} 
		}else {
		   JSONResult.JSONReturnWithData("2", "上传的数据不是多文件的表单", response);	
		}
	}

}
