document.addEventListener("DOMContentLoaded", function () {

    const booksCards = document.getElementById("booksCards");
    const books = [];


    function loadBookList() {
        fetch("/app/books?action=getAll")
                .then(response => response.json())

                .then(data => {
                    console.log(data);                    
                    data.forEach(book => {
                        books.push(book);
                        booksCards.innerHTML += ` <div class="card" style="width: 18rem;">
                            <div class="card-body">
                                <img src="${book.image}" class="card-img-top" alt="">
                                <h5 class="card-title">${book.nombre}</h5>
                                <h6 class="card-subtitle mb-2 text-body-secondary">${book.autor}</h6>
                                <p class="card-text">${book.sinopsis}</p>
                                <a href="#" class="card-link">Precio: ${book.precio}</a>
                                <a href="#" class="card-link">Copias disponibles: ${book.copias}</a>
                                
                            </div>
                        </div>`;
                    });
                });
    }

    booksCards.addEventListener("click", function (e) {
        const clickedCard = e.target.closest(".ident");
        if (!clickedCard) {
            return;
        }

        const bookId = clickedCard.dataset.bookId;

        fetch(`/app/books?action=getDetails&id=${bookId}`)
                .then(response => response.json())
                .then(bookDetails => {
                    const queryParams = new URLSearchParam({
                        id: bookDetails.idlibros
                    });

                    window.location.href = `/app/pages/bookDetails.html?${queryParams.toString()}`;
                })
                .catch(error => console.error("Error en la solicitud GET", error));

    });

    loadBookList();

});




















