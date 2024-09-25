const apiUrl = 'http://localhost:8080/api/inventario'; 

document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('inventarioForm');
    const inventarioTable = document.getElementById('inventarioTable').querySelector('tbody');

    // Obtener todos los elementos del inventario
    function obtenerInventario() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                inventarioTable.innerHTML = '';
                data.forEach(item => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${item.id_item}</td>
                        <td>${item.nombre}</td>
                        <td>${item.tipo}</td>
                        <td>${item.cantidad}</td>
                        <td>${item.precio}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editarInventario(${item.id_item})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="eliminarInventario(${item.id_item})">Eliminar</button>
                        </td>
                    `;
                    inventarioTable.appendChild(row);
                });
            });
    }

    // Crear o actualizar inventario
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const id = form.dataset.id || ''; // Si existe, es una actualización
        const method = id ? 'PUT' : 'POST';
        const url = id ? `${apiUrl}/${id}` : apiUrl;

        const inventario = {
            nombre: document.getElementById('nombre').value,
            tipo: document.getElementById('tipo').value,
            cantidad: document.getElementById('cantidad').value,
            precio: document.getElementById('precio').value
        };

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(inventario)
        })
        .then(response => response.json())
        .then(() => {
            form.reset();
            delete form.dataset.id; // Limpiar el ID almacenado para actualizaciones
            obtenerInventario();
        });
    });

    // Cargar datos en el formulario para editar
    window.editarInventario = function (id) {
        fetch(`${apiUrl}/${id}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('nombre').value = data.nombre;
                document.getElementById('tipo').value = data.tipo;
                document.getElementById('cantidad').value = data.cantidad;
                document.getElementById('precio').value = data.precio;
                form.dataset.id = id; // Almacenar el ID en el formulario para saber que estamos editando
            });
    };

    // Eliminar inventario
    window.eliminarInventario = function (id) {
        fetch(`${apiUrl}/${id}`, { method: 'DELETE' })
            .then(() => obtenerInventario());
    };

    // Cargar los inventarios al iniciar
    obtenerInventario();
});
