<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="br.com.clienteweb.model.Cliente"%>
<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<div>
	<%
		Object msg = request.getAttribute("msg");
	if(msg!=null){
		String msgStr = (String)msg;
		out.print(msg);
	}
	%>
</div>
	Tela de cliente
	<form method="post" action="cliente">
		E-mail:<input type="text" value="" name="email" /> 
		<input type="submit" value="save" />
	</form>

	<%
		List<Cliente> lista = (List<Cliente>) request.getAttribute("lista");
		int i = 0;
		for (Cliente c : lista) {
			out.print(c.getEmail() + "<a href = 'cliente?i="+ i +"'> excluir </a> <br/>");
			i++;
		}
	%>
</body>
</html>