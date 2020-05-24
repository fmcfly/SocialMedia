function validar()
{
	let nombre = document.getElementById("nombreInput").value ;
	
	let apellido = document.getElementById("apellidoInput").value ;
	
	let correo = document.getElementById("correoInput").value ;
	
	let telefono = document.getElementById("telefonoInput").value ;
	
	let password = document.getElementById("passwordInput").value ;
	
	let passwordConfirmacion = document.getElementById('inputPasswordConfirmacion').value;
	
	
	if(nombre == ''){
		alert('No ingresaste el nombre');
		return;
	}else if(apellido == ''){
		alert('No ingresaste apellido');
		return;
	}
	
	if(correo == ''){
		alert('No ingresaste correo');
		return;
	}else{
	   let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	   let validacionCorreo = re.test(String(correo).toLowerCase());
	   if(validacionCorreo == false){
		   alert('El correo que ingresaste no es válido');
		   return;
	   }
	}
	
	if (telefono == ''){
		alert('No ingresaste telefono');
		return;
	}else{
		if(telefono.length < 10){
			alert('La longitud mínima es de 10 digitos');
			return;
		}
	}
	
	if(password != '' && passwordConfirmacion != ''){
		//la validacion de que la contraseña sea de minimo 8 caracteres
		if(password.length >= 8 && password.length < 20){
			if(password == passwordConfirmacion){
				let formRegistro = document.getElementById("registroForm");
				formRegistro.action = "/SocialMedia/UsuarioServlet";
				formRegistro.method = "POST";
				formRegistro.submit();
				
			}else{
				alert('Las contraseñas no coinciden');
				return;
			}
		}else{
			alert('La contraseña debe de ser mayor a 8 y menor a 20');
		}
			
	}else{
		alert('No ingresaste alguna contraseña');
		return;
	}
}