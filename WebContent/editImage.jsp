<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="DAO.ImageDao"%>
<%@page import="Model.Image"%>
<%String imgId = request.getParameter("imageId");
	long imageId = Long.parseLong(imgId);
	ImageDao imagDao = new ImageDao();
	Image image = imagDao.getImage(imageId);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		
	<title>Image Management Utility</title>
</head>
 
<body>
	<div class="container" align="center" style="margin-top:50px">
	<form action = "editImage?id=<%=request.getParameter("imageId") %>" method="post">
		<table cellpadding="10">
		 <tr>
			<td style="border-style: solid; border-left-color: aliceblue; border-right-color: aliceblue; background-color: aliceblue; border-bottom-color: lightskyblue; border-top-color: lightskyblue;">
				<h2 style="margin: 0px; color: slateblue;">
					<b>Edit Image :</b>
				</h2>
			</td>
		 </tr>
		 
		 <tr>
			<td style="background-color: aliceblue;">
				<table>
					 <tr>
						<td style="width: 200px; padding: 10px;">ImageID :</td>
						<td><input type="text"  name="imageId" value="<%=image.getImageId() %>" readonly="readonly"/>
						</td>
					 </tr>
					 <tr>
						<td style="width: 200px; padding: 10px;">New Image Name :</td>
						<td><input type="text" name="editedName"  value="<%=image.getImageName() %>" />
						</td>
					 </tr>					 
				 </table>				
			 </td>
		 </tr>
		 
		 <tr>
			<td style="text-align: right; margin-right: 10px; text-align: right; margin-right: 10px; background-color: aliceblue; border-style: solid; border-top-color: lightskyblue; border-bottom-color: lightskyblue; border-left-color: aliceblue; border-right-color: aliceblue;">
			<button type="submit" >Edit</button>
			</td>
		 </tr>
		 
	    </table>
		
     </form>
   </div>
</body>
</html>