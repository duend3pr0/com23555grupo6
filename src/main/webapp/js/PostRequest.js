document.addEventListener("DOMContentLoaded",function(){
    
   const addBookForm = document.getElementById("addBookForm");
   const parrafoAlerta = document.createElement("p");
   const nombre = document.getElementById("nombre") ;
   const autor = document.getElementById("autor") ;
   const cantPaginas = document.getElementById("cantPaginas") ;
   const copias = document.getElementById("copias") ;
   const sinopsis = document.getElementById("sinopsis") ;
   const precio = document.getElementById("precio") ;
   
    
   const imagen = document.getElementById("imagen");
   const imagenPreview = document.getElementById("imagenPreview");
   const imagenContainer = document.getElementById("imagenContainer");
   
   addBookForm.addEventlistener("submit", function(e){
       e.preventDefault();
       
   });
   
   
   
   
   
   imagen.addEventListener("change", function(){
       const selectedImage = imagen.files[0];
       
       if(selectedImage){
           const reader = new FileReader();
           reader.onload = function(e){
               imagenPreview.src = e.target.result;
               imagenContainer.classList.remove("d-none");               
           };
           
           reader.readAsDataURL((selectedImage));
       }else{
               imagenPreview.src = "";
           }       
   });
});
