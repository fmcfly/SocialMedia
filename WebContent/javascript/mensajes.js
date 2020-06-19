setInterval(function(){
					console.log("Me llaman cada 3 segundos"); 
					
					/*$.post("/SocialMedia/MensajesServlet?idAmigo="+idAmigo,function(){
						window.location.href = "/SocialMedia/chat.jsp";
					});*/
			}, 10000);

function enviarMensaje(){
	console.log("Enviar mensaje");
	let formMensaje = document.getElementById("envio");
	formMensaje.action = "/SocialMedia/EnvioMensajes";
	formMensaje.method = "POST";
	formMensaje.submit();
}

function envioMensaje(){
	
	 $.ajax({
	        url:"/SocialMedia/Messages",
	        method:"POST", //First change type to method here

	        data:{
	        	"message": $("#message").val(), // Second add quotes on the value.
	        },
	        success:function(response) {
	        	console.log(response);
	        	let mensajeEnviado = document.getElementById("message");
	        	$("#newMessage").before("<div class='mensajeUsuario'>"+
	        				"<div class='card border border-success' style='width: 18rem;'> " +
	        			  "<div class='card-body'>"+
	        			    "<p class='card-text'>"+mensajeEnviado.value+"</p>"+
	        			    "<p class='card-link'>2020/06/19</p>"+
	        			  "</div>"+
	        			"</div>"+
	        			  "</div>");
	        	mensajeEnviado.value = "";
	       },
	       error:function(){
	    	   console.log("error");
	       }

	      });
}