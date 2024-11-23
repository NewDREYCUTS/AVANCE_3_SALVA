$(document).ready(function() {
    var button_modal = $('#button_modal');
    var menu_lateral = $('#menu_lateral');

    // Función para alternar clases y abrir/cerrar el menú lateral
    button_modal.on('click', function() {
        if (menu_lateral.hasClass("CerrarMenuLateral")) {
            menu_lateral.removeClass("CerrarMenuLateral").addClass("AbrirMenuLateral");
        } else {
            menu_lateral.removeClass("AbrirMenuLateral").addClass("CerrarMenuLateral");
        }
    });

    // Función para ajustar el menú según el ancho de la ventana
    function ajustarMenuSegunAnchoVentana() {
        if ($(window).width() < 800) {
            menu_lateral.addClass("CerrarMenuLateral").removeClass("AbrirMenuLateral");
        } else {
            menu_lateral.removeClass("CerrarMenuLateral").addClass("AbrirMenuLateral");
        }
    }
    // Llamamos a la función para ajustar el menú al cargar la página
    ajustarMenuSegunAnchoVentana();
    // También escuchamos el evento 'resize' para ajustar el menú cuando se cambie el tamaño de la ventana
    $(window).on('resize', ajustarMenuSegunAnchoVentana);
});

$(document).ready(function() {
    function maxWidth() {
        if ($(window).width() < 400) {
            $('body').addClass("narrow-width");
        } else {
            $('body').removeClass("narrow-width");
        }
    }
    maxWidth(); // Llamamos a la función al cargar la página
    $(window).on('resize', maxWidth); // Escuchamos el evento de redimensionamiento de la ventana
});
