document.addEventListener("DOMContentLoaded", function () {
    const queryParams = new URLSearchParams(window.location.search);

    const bookDetailId = {
        id: queryParams.get("id")
    };

    const bookDetailsContainer = document.getElementById("bookDetails");
    const btnEliminarElement = document.getElementById("btnEliminar");
    const btnModificarElement = document.getElementById("btnModificar");
    const btnGuardarElement = document.getElementById("btnGuardar");
    const btnContainerElement = document.getElementById("btnContainer");

    let objetoLibro = {
        id: 0,
        nombre: "",
        autor: "",
        cantPaginas: 0,
        sinopsis: "",
        copias: 0,
        precio: 0,
        imagen: ""
    };

    function loadBook() {

        fetch(`/app/books?action=getById&id=${bookDetailId.id}`)
                .then(response => response.json())
                .then(data => {
                    bookDetailsContainer.innerHTML += `
                        <div class="col-md-6 text-center">
                            <div class="clearfix">
                                <img src="data:image/jpeg;base64,${data.imagenBase64}" class="my-4" style="width: 75%" alt="imagen de libro"/>
                            </div>
                        </div>
                        <div class="card-body col-md-6">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <h2 class="card-title">${data.nombre}</h2>
                                </li>
                                <li class="list-group-item">Autor: ${data.autor}</li> 
                                <li class="list-group-item">Paginas: ${data.cantPaginas}</li> 
                                <li class="list-group-item">Sinopsis: ${data.sinopsis}</li> 
                                <li class="list-group-item">Copias Disponibles: ${data.copias}</li> 
                                <li class="list-group-item">
                                    <h5>Precio: ${data.precio}</h5>  
                                </li> 
                            </ul>
                        </div>
                    `;
                    objetoLibro.id = data.id;
                    objetoLibro.nombre = data.nombre;
                    objetoLibro.autor = data.autor;
                    objetoLibro.cantPaginas = data.cantpaginas;
                    objetoLibro.sinopsis = data.sinopsis;
                    objetoLibro.copias = data.copias;
                    objetoLibro.precio = data.precio;
                    objetoLibro.imagen = data.imagen;

                });
    }
    
     btnEliminarElement.addEventListener('click',function(){
        fetch(`/app/books?action=delete&id=${bookDetailId.id}`,{
            method:"DELETE"
        })
                .then(response => response.json())
                .then(data=>{
                    if(data.success){
                        console.log("estoy dentro del if");
                        window.location.href = `/app/index.html`;
                    }
                });
    });
    
    btnModificarElement.addEventListener('click', function(){
        btnModificarElement.classList.add("d-none");
        btnEliminarElement.classList.add("d-none");
        btnGuardarElement.classList.remove("d-none");
        
        bookDetailsContainer.innerHTML = `
            <div class="col-md-6 text-center">
                <div class="clearfix">
                    <img src="data:image/jpeg;base64,${objetoLibro.imagen}" class="my-4" style="width: 75%" alt="imagen de portada">
                </div>
            </div>
            <div class="card-body col-md-6">
                <form  class="mb-4" id = "updateBookForm" enctype="multipart/form-data">
                    <div class="card-body">
                        <div class="row">
                            <div class="form-floating my-3">
                                <input type="text" class="form-control" name="nombre" id="nombre" placeholder="nombre" value="${objetoLibro.nombre}" required/>
                                <label for="nombre">Titulo</label>
                            </div>

                            <div class="form-floating my-3">
                                <input type="text" class="form-control" name="autor" id="autor" placeholder="autor" value="${objetoLibro.autor}" required/>
                                <label for="autor">Autor</label>
                            </div>

                            <div class="form-floating my-3">
                                <input type="number" class="form-control" name="cantPaginas" id="cantPaginas" placeholder="cantPaginas" value="${objetoLibro.cantPaginas}" required/>
                                <label for="cantPaginas">Cantidad de Páginas</label>
                            </div>

                            <div class="form-floating my-3">
                                <input type="number" class="form-control" name="copias" id="copias" placeholder="copias" value="${objetoLibro.copias}" required/>
                                <label  for="copias">Cantidad de Copias</label>
                            </div>

                            <div class="form-floating">
                                <textarea class="form-control" placeholder="Escriba la sinopsis aqui" name="sinopsis" id="sinopsis">${objetoLibro.sinopsis}</textarea>
                                <label for="sinopsis">Sinopsis</label>
                            </div>

                            <div class="form-floating my-3">
                                <input type="number" class="form-control" name="precio" id="precio" placeholder="precio" value="${objetoLibro.precio}" required/>
                                <label  for="precio">Precio</label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        `;
    });
    
    btnGuardarElement.addEventListener('click' , function(e){
        
        e.preventDefault();
        const formulario = new FormData();
        
        formulario.append("action", "update");
        formulario.append("id", bookDetailId.id);
        formulario.append("nombre", document.getElementById("nombre").value);
        formulario.append("autor", document.getElementById("autor").value);
        formulario.append("cantPaginas", document.getElementById("cantPaginas").value);
        formulario.append("copias", document.getElementById("copias").value);
        formulario.append("sinopsis", document.getElementById("sinopsis").value);
        formulario.append("precio", document.getElementById("precio").value);
        formulario.append("imagen", objetoLibro.imagen);
        
        fetch(`/app/books`,{
           method:"POST",
           body: formulario
        })
                .then(response => {
                    if(!response.ok){
                        throw new Error(`Error en la solicitud: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data =>{
                    if(data.success === "true"){
                        window.location.href = `/app/index.html`;
                    }
                    else{
                        console.error("La solicitud fue exitosa, pero la respuesta indica un error: "+data.message);
                    }
                });
    });

    loadBook();
});

