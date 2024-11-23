// Function to close the error alert after 3 seconds
setTimeout(function () {
  cerrarErrorAlert();
}, 4500);

// Function to close the error alert
function cerrarErrorAlert() {
  var errorAlerts = document.querySelectorAll(".alerta");
  for (let error of errorAlerts) {
    if (error) {
      error.style.display = "none";
    }
  }
}

// Función para validar si el DNI tiene 8 dígitos y luego verificar en el backend
function validarDNI(dni) {
  const validationMessage = document.getElementById("dniValidationMessage");

  // Validación local: verificar si tiene 8 dígitos
  if (!/^\d{8}$/.test(dni)) {
    validationMessage.textContent = "El DNI debe tener exactamente 8 dígitos.";
    validationMessage.className = "text-red-500"; // Mensaje en rojo
    bloquearCampos(); // Bloquea los campos mientras el formato no sea válido
    return; // Detener ejecución si el formato no es válido
  }

  // Si el formato es válido, continuamos con la verificación en el backend
  fetch(`/validar-dni/${dni}`) // Endpoint para validar el DNI
    .then((response) => response.json())
    .then((data) => {
      if (data.existe) {
        validationMessage.textContent = "DNI ya registrado.";
        validationMessage.className = "text-red-500"; // Mensaje en rojo
        bloquearCampos(); // Bloquea los demás campos del formulario
      } else {
        validationMessage.textContent = "DNI disponible.";
        validationMessage.className = "text-green-500"; // Mensaje en verde
        desbloquearCampos(); // Desbloquea los demás campos
      }
    })
    .catch((error) => {
      console.error("Error al verificar el DNI:", error);
      desbloquearCampos(); // Siempre desbloquear en caso de error
    });
}

// Función para bloquear todos los campos excepto el DNI
function bloquearCampos() {
  const formulario = document.getElementById("formulario");
  const inputs = formulario.querySelectorAll("input, select, textarea"); // Todos los campos

  inputs.forEach((input) => {
    if (input.id !== "numero_documento") {
      input.disabled = true; // Bloquea todos excepto el DNI
    }
  });
}

// Función para desbloquear todos los campos
function desbloquearCampos() {
  const formulario = document.getElementById("formulario");
  const inputs = formulario.querySelectorAll("input, select, textarea"); // Todos los campos

  inputs.forEach((input) => {
    input.disabled = false; // Desbloquea todos los campos
  });
}

// Detectar cuando el usuario escribe en el campo de DNI
const dniInput = document.getElementById("numero_documento");

dniInput.addEventListener("input", () => {
  const dni = dniInput.value.trim();
  validarDNI(dni); // Llamamos a la función de validación al escribir
});



// Validación del campo Nombre (solo letras)
function validarNombre() {
  const nombreInput = document.getElementById("nombre");
  const validationMessage = document.getElementById("nombreValidationMessage");
  const nombre = nombreInput.value.trim();

  if (!/^[a-zA-ZÁÉÍÓÚáéíóúñÑ\s]+$/.test(nombre)) {
    validationMessage.textContent = "El nombre solo debe contener letras.";
    validationMessage.className = "text-red-500";
    return false;
  }
  validationMessage.textContent = "";
  return true;
}

// Validación del campo Primer Apellido (solo letras)
function validarPrimerApellido() {
  const primerApellidoInput = document.getElementById("primer_apellido");
  const validationMessage = document.getElementById("primerApellidoValidationMessage");
  const primerApellido = primerApellidoInput.value.trim();

  if (!/^[a-zA-ZÁÉÍÓÚáéíóúñÑ\s]+$/.test(primerApellido)) {
    validationMessage.textContent = "El primer apellido solo debe contener letras.";
    validationMessage.className = "text-red-500";
    return false;
  }
  validationMessage.textContent = "";
  return true;
}

// Validación del campo Segundo Apellido (solo letras)
function validarSegundoApellido() {
  const segundoApellidoInput = document.getElementById("segundo_apellido");
  const validationMessage = document.getElementById("segundoApellidoValidationMessage");
  const segundoApellido = segundoApellidoInput.value.trim();

  if (!/^[a-zA-ZÁÉÍÓÚáéíóúñÑ\s]+$/.test(segundoApellido)) {
    validationMessage.textContent = "El segundo apellido solo debe contener letras.";
    validationMessage.className = "text-red-500";
    return false;
  }
  validationMessage.textContent = "";
  return true;
}

// Validación del Teléfono (9 dígitos)
function validarTelefono() {
  const telefonoInput = document.getElementById("telefono");
  const validationMessage = document.getElementById("telefonoValidationMessage");
  const telefono = telefonoInput.value.trim();

  if (!/^\d{9}$/.test(telefono)) {
    validationMessage.textContent = "El teléfono debe tener exactamente 9 dígitos.";
    validationMessage.className = "text-red-500";
    return false;
  }
  validationMessage.textContent = "";
  return true;
}

// Validación del Correo (formato válido)
function validarCorreo() {
  const correoInput = document.getElementById("correo");
  const validationMessage = document.getElementById("correoValidationMessage");
  const correo = correoInput.value.trim();

  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo)) {
    validationMessage.textContent = "El correo debe tener un formato válido.";
    validationMessage.className = "text-red-500";
    return false;
  }
  validationMessage.textContent = "";
  return true;
}

// Validación de Contraseña (campo obligatorio)
function validarContrasena() {
  const contrasenaInput = document.getElementById("contrasena");
  const validationMessage = document.getElementById("contrasenaValidationMessage");
  const contrasena = contrasenaInput.value.trim();

  // Validación de campo obligatorio
  if (contrasena === "") {
    validationMessage.textContent = "La contraseña es obligatoria.";
    validationMessage.className = "text-red-500";
    return false;
  }

  // Validación de longitud mínima
  if (contrasena.length < 8) {
    validationMessage.textContent = "La contraseña debe tener al menos 8 caracteres.";
    validationMessage.className = "text-red-500";
    return false;
  }

  // Validación de al menos una letra mayúscula
  const mayusculaRegex = /[A-Z]/;
  if (!mayusculaRegex.test(contrasena)) {
    validationMessage.textContent = "La contraseña debe contener al menos una letra mayúscula.";
    validationMessage.className = "text-red-500";
    return false;
  }

  // Validación de al menos un número
  const numeroRegex = /[0-9]/;
  if (!numeroRegex.test(contrasena)) {
    validationMessage.textContent = "La contraseña debe contener al menos un número.";
    validationMessage.className = "text-red-500";
    return false;
  }

  // Validación de al menos un carácter especial
  const especialRegex = /[!@#$%^&*(),.?":{}|<>]/;
  if (!especialRegex.test(contrasena)) {
    validationMessage.textContent = "La contraseña debe contener al menos un carácter especial.";
    validationMessage.className = "text-red-500";
    return false;
  }

  // Si todas las validaciones pasan
  validationMessage.textContent = "Contraseña válida.";
  validationMessage.className = "text-green-500";
  return true;
}


// Detectar eventos de input para realizar validaciones dinámicas
document.getElementById("nombre").addEventListener("input", validarNombre);
document.getElementById("primer_apellido").addEventListener("input", validarPrimerApellido);
document.getElementById("segundo_apellido").addEventListener("input", validarSegundoApellido);
document.getElementById("telefono").addEventListener("input", validarTelefono);
document.getElementById("correo").addEventListener("input", validarCorreo);
document.getElementById("contrasena").addEventListener("input", validarContrasena);

// Validar todos los campos antes de enviar el formulario
document.getElementById("formulario").addEventListener("submit", (e) => {
  if (
    !validarNombre() ||
    !validarPrimerApellido() ||
    !validarSegundoApellido() ||
    !validarTelefono() ||
    !validarCorreo() ||
    !validarContrasena()
  ) {
    e.preventDefault(); // Evitar el envío si hay errores
  }
});





