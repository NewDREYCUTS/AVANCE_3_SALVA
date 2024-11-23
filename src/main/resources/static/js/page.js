

$(document).ready(function(){
    $('#btn_carrito').on('click', ()=>{
     $('#ventana_carrito').addClass('w-96 abrirAnimacion').removeClass('w-0')
    })
 });
 $(document).ready(function(){
     $('#cerrar_carrito').on('click', ()=>{
      $('#ventana_carrito').addClass('w-0').removeClass('w-96')
     })
  });
  $(document).ready(function(){
     $('.count_plus').on('click', function() {
         var producto = $(this).closest('.item-carrito');
         var contador = parseInt(producto.find('.result').text());
         producto.find('.result').text(contador + 1);
     });
     
     $('.count_low').on('click', function() {
         var producto = $(this).closest('.item-carrito');
         var contador = parseInt(producto.find('.result').text());
         if (contador > 0) {
             producto.find('.result').text(contador - 1);
         }
     });
 });
 