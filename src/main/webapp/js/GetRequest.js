document.addEventListener("DOMContentLoaded", function(){
   
    const booksCards = document.getElementById("booksCards");
    const books = [];  
    
    
    function loadBookList(){
        fetch("/app/books?action=getAll")
                .then(response=> response.json())
                
                .then(data =>{
                   booksCards.innerHTML = "No cambio nada";
                data.forEach(book=>{
                        books.push(book);
                        booksCards.innerHTML = "Pepe" ;
                    }); 
                });
    }
    
    booksCards.addEventListener("click", function(e){
       const clickedCard = e.target.closest(".ident");
       if(!clickedCard){
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




















