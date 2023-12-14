document.addEventListener("DOMContentLoaded",function(){
    const imagen = document.getElementById("imagen");
    const imagenPreview = document.getElementById("imagenPreview");
    const imagenContainer = document.getElementById("imagenContainer");
    
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
 