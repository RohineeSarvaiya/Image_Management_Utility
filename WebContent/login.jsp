<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">		
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<title>Login</title>		
</head>
	
<body>	
	<div align="center" style="margin-top:150px">
	<form name="form" action="LoginController" method="post" onsubmit="return InputValidation();">
	
	   <table cellpadding="10">
		 <tr>
			<td style="border-style: solid; border-left-color: aliceblue; border-right-color: aliceblue; background-color: aliceblue; border-bottom-color: lightskyblue; border-top-color: lightskyblue;">
				<h2 style="margin: 0px; color: slateblue;">
					<b>Login :</b>
				</h2>
			</td>
		 </tr>
		 
		 <tr>
			<td style="background-color: aliceblue;">
				<table>
					 <tr>
						<td style="width: 200px; padding: 10px;">Username :</td>
						<td><h3 style="color: red; display: inline;">*</h3> 
						<input type="text" name="username" id="username" />
						</td>
					 </tr>
					 <tr>
						<td style="width: 200px; padding: 10px;">Password :</td>
						<td><h3 style="color: red; display: inline;">*</h3> 
						<input type="password" name="password" id="password" />
						</td>
					 </tr>
					 
				 </table>
				 <a style="padding-left:210px ;  text-decoration: underline; font-size:13px;" href="" >Forgotten your password?</a>
			 </td>
		 </tr>
		 
		 <tr>
			<td style="text-align: right; margin-right: 10px; text-align: right; margin-right: 10px; background-color: aliceblue; border-style: solid; border-top-color: lightskyblue; border-bottom-color: lightskyblue; border-left-color: aliceblue; border-right-color: aliceblue;">
			<input type="submit" value="Login>>" />
			</td>
		 </tr>
		 
	 </table>
	 
  </form>
</div>
</body>

<script>
	function InputValidation(){
		if (document.form.username.value == "")
		{
			alert ( "Please enter User Name." );
			document.form.username.focus();
			return false;
		}
		if (document.form.password.value == "")
		{
			alert ( "Please enter password." );
			document.form.password.focus();
			return false;
		}
		
		return true;
	}
</script>

</html>







