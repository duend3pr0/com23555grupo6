document.addEventListener("DOMContentLoaded", function(){
    const bookCards = document.getElementById("bookCards");
    const books =[];
    
    function loadBookList(){
        fetch("/app/books?action=getAll")
                .then(response=> response.json())
                .then(data =>{
                    data.forEach(book =>{
                        books.push(book);
                        bookCards.innerHTML += `
                            <div class="col-md-3 mb-4 ident" data-book-id="${book.idlibros}">
                                <div class="card h-100 animate-hover-card">
                                    <img src="data:image/jpeg;base64,${book.imagenBase64}" class="card-img-top h-50  img-fluid cardImage" alt="Imagen Portada de Libro">
                                    <div class="card-body ">
                                        <h5 class="card-tittle">${book.nombre}</h5>
                                        <p class="card-text">${book.sinopsis}</p>
                                    </div>
                                </div>
                            </div>
                        `;
                    });
                });
    }
    
    function filterBooks(palabra){
        const librosFiltrados = books.filter( book=>{
            return book.nombre.toLowerCase().includes(palabra.toLowerCase());
        });
        
        bookCards.innerHTML = "";
        
         librosFiltrados.forEach(book => {
            const card = document.createElement("div");
            card.className = "col-md-3 mb-4 ident";
            card.setAttribute("data-book-id", book.idlibros);
            card.innerHTML = `
            <div class="card h-100 animate-hover-card">
                <img src="data:image/jpeg;base64,${book.imagenBase64}" class="card-img-top h-75" alt="imagen de portada">
                <div class="card-body">
                    <h5 class="card-title">${book.nombre}</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                </div>
            </div>
        `;
            bookCards.appendChild(card);
        });
    }
    
    //Evento que lanza la funcion de filtrar y agregar tarjetas de libros.
    const searchForm = document.querySelector("form[role='search']");
    searchForm.addEventListener("submit", function(e){
       e.preventDefault();
       const searchTerm = searchForm.querySelector("input[type='search']").value;
       filterBooks(searchTerm);
    });
    
    //evento para mostrar detalles de libros
    
    bookCards.addEventListener("click", function(e){
        const clickedCard = e.target.closest(".ident");
        if(!clickedCard){
            return;
        }
        
        const bookId = clickedCard.dataset.bookId;
        
        fetch(`/app/books?action=getDetails&id=${bookId}`)
                .then(response => response.json())
                .then(bookDetails =>{
                    const queryParams =  new URLSearchParams({
                       id : bookDetails.idlibros
                    });
                    
                    window.location.href = `/app/pages/bookDetails.html?${queryParams.toString()}`;
                })
                .catch(error => console.error("Error en la solicitud GET:", error));
    });
    
    loadBookList();
    
    
});












































