async function api() {
    const consulta = await fetch('https://tokkobroker.com/api/v1/location/quicksearch/?format=json&lang=es_ar&q=LIMA');
    const respuesta = await consulta.json();
    console.log(respuesta);
}
