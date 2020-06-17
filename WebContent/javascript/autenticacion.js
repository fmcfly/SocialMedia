function cerrarSesion(){
	$.get("/SocialMedia/LoginServlet",function(){
		window.location.href = "/SocialMedia/login.jsp";
	});
}