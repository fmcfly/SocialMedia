function addFriend(idUsuario,criterioBusqueda){
	
	if(criterioBusqueda === undefined){
		criterioBusqueda = '';
	}
	$.post("/SocialMedia/PerfilServlet?idUsuario="+idUsuario+"&nombre="+criterioBusqueda,function(){
		window.location.href = "/SocialMedia/PerfilServlet?nombre="+criterioBusqueda;
		//location.reload();
	});	
}