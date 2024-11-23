var agregar = document.getElementById("agregar");
var modal = document.getElementById("modal");
agregar.addEventListener("click",()=>{
  modal.classList.remove("hidden")
})

var cerrar_modal = document.getElementById("cerrar_modal")
cerrar_modal.addEventListener("click",()=>{
  modal.classList.add("hidden")
})

function buscarTabla() {
    // Obtener el valor de búsqueda del input
    var input = document.getElementById("searchInput");
    var filter = input.value.toLowerCase();
    // Obtener la tabla y sus filas
    var table = document.getElementById("miTabla");
    var tr = table.getElementsByTagName("tr");

    // Iterar sobre todas las filas de la tabla
    for (var i = 1; i < tr.length; i++) {
        // Ocultar la fila inicialmente
        tr[i].style.display = "none";
        // Obtener todas las celdas de la fila actual
        var td = tr[i].getElementsByTagName("td");
        // Iterar sobre todas las celdas
        for (var j = 0; j < td.length; j++) {
            if (td[j]) {
                // Si el texto de la celda coincide con el filtro, mostrar la fila
                if (td[j].innerHTML.toLowerCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                    break;
                }
            }
        }
    }
}
