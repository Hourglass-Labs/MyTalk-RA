package it.hourglass.myTalk.server.model.businesslogic;



import java.io.IOException;
import java.util.List;


import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.*;




import it.hourglass.myTalk.client.rpcservice.FileService;
import it.hourglass.myTalk.client.wrappers.ConsoleLog;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class FileServiceImpl extends RemoteServiceServlet implements
FileService {

//code omitted

private FileItem uploadedFileItem;

@Override
protected void service(final HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

boolean isMultiPart = ServletFileUpload
	.isMultipartContent(new ServletRequestContext(request));

if(isMultiPart) {
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
try {
	@SuppressWarnings("unchecked")
	List<FileItem> items = upload.parseRequest(request);
	uploadedFileItem = items.get(0); // we only upload one file

	if(uploadedFileItem == null) {
		super.service(request, response);
		return;
		} 
	else if(uploadedFileItem.getFieldName().equalsIgnoreCase("uploadFormElement")) {
		String fileName = uploadedFileItem.getName();
		System.out.println(fileName);
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().print("OK");
		response.flushBuffer();
	}

	} catch(FileUploadException e) {
		ConsoleLog.consoleLog(e.toString());
	}
}

else {
super.service(request, response);
return;
}
}

@Override
public String uploadAttachement(String caseId, String fieldName,
		boolean isNewCase) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean deleteAttachement(String filePath, int caseID, String fieldName) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public String updateFileName(String name) {
	// TODO Auto-generated method stub
	return null;
}

//code omitted
}