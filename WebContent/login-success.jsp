<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="Model.User"%>
<%@page import="Model.Image"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Iterator"%> 

<%@page import="Controller.UploadImage"%>
<%User user=(User)session.getAttribute("user");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
		<link href="CSS/loginStyle.css" rel="stylesheet" type="text/css" />	 
		
		<title>Image Management Utility</title>	

</head>

<body>
<div class ="container" id="mainDiv">
	<div class ="container"  id="header">
			<h3><b>Image Management Utility</b></h3>
	</div>
		
	<div  class ="container" id="imageUpload" style="padding-left:20px ; padding-right:20px">
		<h4>Please Select an Image File to Upload (Max Size 1 MB)</h4>
		<br>
		<br>
		<form action="UploadImage" method="post" enctype="multipart/form-data" >
			<div >
				<input type="text" id="filename" />
				<input type="button" id= "browse"  value="Browse.." onclick="imageSelection()">
				<input type="file" name="image" accept="image/*" id="upload" hidden onchange="imageUpdation()" />
				<div style="float:right">
				<button type="submit"   onclick="return checkFilePresence()">Submit</button> &nbsp;
				<button type="reset" >Cancel</button>
				</div>
			</div>
		</form>
	</div>
	
	<div class ="container" id="imageTable" style="padding-left:20px; padding-right:20px" >
		<h4 align="left">Uploaded Images</h4>
		<br>
		<table class="table table-bordered">			
			<tr>
				<td><b>S.No.</b></td>
				<td><b>Name</b></td>
				<td><b>Size</b></td>
				<td><b>Preview</b></td>
				<td><b>Actions</b></td>
			</tr>
			
		   <tbody>
			<%Collection<Image> images = user.getImages();
			  int i = 0;
   	          if (!images.isEmpty())
   	          {
  				 for (Image image : images) 
  				 {      i++;
             %>				
		   <tr>
				<td scope="row"><%=i %></td>
				<td><%=image.getImageName() %></td>
				<td><%=image.getImageSize() %></td>
				<td><img src="fetchImage?imageId=<%=(image.getImageId()) %>" height=100 width=100 alt="No image"></img></td>
				<td>
					<form action="editImage.jsp?imageId=<%=image.getImageId() %>" method="post">
						<button ><span class="glyphicon glyphicon-pencil"></span></button>
					</form>
					<br />
					<form action="deleteImage?imageId=<%=image.getImageId() %>" method="post">
						<button ><span class="glyphicon glyphicon-remove"></span></button>							
					</form>						
				</td>
			</tr>
			<%}
   	            }
             %>
		  </tbody>	
		</table>
	</div>
	
	<div class="container" style="padding-left:20px">
	<% double totalSize= UploadImage.getTotalSize(user.getUsername());%>
			<h5>Total size of images uploaded= <b><%=totalSize%>KB</b></h5>
	</div>
	
	<div class = "container" >
		<form action="LogoutController" align="center">
			<button type="submit" >Logout</button>
		</form>
	</div>
	
</div>
</body>
<script>
		function imageSelection(e)
		{
			var u = document.getElementById("upload");
			u.click();
			e.stopPropagation();
			e.preventDefault();
		}
		
		function imageUpdation()
		{	
			document.getElementById("filename").value = document.getElementById("upload").files[0].name;			
		} 
		
		function checkFilePresence()
		{
			var inputFile = document.getElementById("upload");
			if (inputFile.value == null || inputFile.files.length == 0) 
			{
				alert("Please select a file");
				return false;
			} 
			else 
			{
				return true;
			}
		}

</script> 
</html>

