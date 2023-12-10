document.addEventListener("DOMContentLoaded", function(){
   
    const queryParams = new URLSearchParams(window.location.search);
    
    const bookDetailId = {
        id:queryParams.get("id")
    };
    
    const bookDetailsContainer = document.getElementById("bookDetails");
    const btnEliminarElement = document.getElementById("btnEliminar");
    const btnModificarElement = document.getElementById("btnModificar");
    const btnGuardarElement = document.getElementById("btnGuardar");
    const btnContainerElement = document.getElementById("btnContainer");
    
    
    let objetolibro = {
        id: 0,
        nombre: "",
        autor: "",
        cantPaginas: 0,
        sinopsis : "",
        precio: 0,
        imagen: ""        
    }
    
    function loadBook(){
        fetch(`app/books/action=getById&id${bookDetailId}`)
                .then(response => response.json())
                .then(data =>{
                    bookDetailsContainer.innerHTML += `
                        <div class="col-md-6 text-center">
                            <div class="clearfix">
                                <img src="data:image/jpeg;base64,${data.imagenBase64}" class="my-4" style="width: 75%" alt="imagen de libro">
                                    
                            </div>
                        </div>
                        <div class="card-body col-md-6">
                            <ul class="list-group list-group- flush">
                                <li class="list-group-item">
                                    <h2 class="card-title">${data.nombre}</h2>
                                </li>
                                <li class="list-group-item">Autor:${data.autor}</li>    
                                <li class="list-group-item">Paginas:${data.cantPaginas}</li>    
                                <li class="list-group-item">Sinopsis:${data.sinopsis}</li>    
                                <li class="list-group-item">Copias disponibles:${data.copias}</li>  
                                <li class="list-group-item">
                                    <h5 class>Precio: ${data.precio}</h5>
                                }</li>            
                            </ul>    
                        </div>    
            
            
            
                    `;
            
            
                objetoLibro.id= data.id;    
                objetoLibro.nombre= data.nombre;
                objetoLibro.autor= data.autor;
                objetoLibro.cantPaginas= data.cantPaginas;
                objetoLibro.sinopsis= data.sinopsis;
                objetoLibro.copias= data.copias;
                 objetoLibro.precio= data.precio;
                  objetoLibro.imagen= data.imagen;               
                });
    }
    loadBook();
});
























