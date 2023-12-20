package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import model.Book;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

@WebServlet("/books")
@MultipartConfig(
        location = "/media/temp",
        fileSizeThreshold = 1024 * 1024, //Tamaño umbral 1MB
        maxFileSize = 1024 * 1024 * 5, //Tamaño maximo de archivo en bytes 5MB
        maxRequestSize = 1024 * 1024 * 10 // Tamaño maximo de request en bytes 10MB
)
public class BookServletController extends HttpServlet{
    
    List<Book> bookList = new ArrayList();
    ObjectMapper mapper = new ObjectMapper();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        req.setCharacterEncoding("UTF-8");
        String route = req.getParameter("action");
        System.out.println("parametro: "+ route);
        switch (route){
            case "getAll"->{
                res.setContentType("application/json; charset=UTF-8");
                bookList = BookDAO.seleccionar();
                
                for(Book book : bookList){
                    byte[] imagenBytes = book.getImagen();
                    String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
                    book.setImagenBase64(imagenBase64);
                }
                
                mapper.writeValue(res.getWriter(), bookList);
            }
            
            case "getDetails"->{
                String bookId = req.getParameter("id");
                
                Book bookDetails = BookDAO.seleccionarPorId(Integer.parseInt(bookId));
                
                res.setContentType("application/json");
                mapper.writeValue(res.getWriter(),bookDetails);
            }
            
            case "getById"->{
                
                int id = Integer.parseInt(req.getParameter("id"));
                
                res.setContentType("application/json");
                Book bookDetails = BookDAO.seleccionarPorId(id);
                
                byte[] imagenBytes = bookDetails.getImagen();
                String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
                bookDetails.setImagenBase64(imagenBase64);
                
                mapper.writeValue(res.getWriter(), bookDetails);
            }
            
            default->{
                    System.out.println("parametro no valido.");
            }
            
            
                
        }   
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        String route = req.getParameter("action");
        
        switch(route){
            case "add" ->{
                String nombre = req.getParameter("nombre");
                String autor = req.getParameter("autor");
                int cantPaginas = Integer.parseInt(req.getParameter("cantPaginas"));
                int copias = Integer.parseInt(req.getParameter("copias"));
                String sinopsis = req.getParameter("sinopsis");
                double precio = Double.parseDouble(req.getParameter("precio"));
                
                Part filePart = req.getPart("imagen");
                byte [] imagenBytes = IOUtils.toByteArray(filePart.getInputStream());
                
                Book newBook = new Book(nombre,autor,cantPaginas,precio,sinopsis, copias, imagenBytes);
                
                BookDAO.insertar(newBook);
                
                res.setContentType("application/json");
                Map <String, String> response = new HashMap();
                response.put("message", "Libro guardado exitosamente!!!");
                mapper.writeValue(res.getWriter(), response);
            }
            
            case "update"->{
                
                try{
                    int id= Integer.parseInt(req.getParameter("id"));
                    String nombre = req.getParameter("nombre");
                    String autor = req.getParameter("autor");
                    int cantPaginas = Integer.parseInt(req.getParameter("cantPaginas"));
                    int copias = Integer.parseInt(req.getParameter("copias"));
                    String sinopsis = req.getParameter("sinopsis");
                    double precio = Double.parseDouble(req.getParameter("precio"));

                    Part filePart = req.getPart("imagen");
                    byte[] imageBytes = IOUtils.toByteArray(filePart.getInputStream());

                    Book libro = new Book(id,nombre, autor, cantPaginas, precio, sinopsis, copias, imageBytes);

                    BookDAO.actualizar(libro);

                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");

                    Map <String, String> response = new HashMap<>();
                    response.put("success", "true");
                    mapper.writeValue(res.getWriter(), response);
                }catch(ServletException | IOException | NumberFormatException e){
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    
                    Map <String, String> responseFail = new HashMap<>();
                    responseFail.put("success", "false");
                    responseFail.put("message", e.getMessage());
                    mapper.writeValue(res.getWriter(), responseFail);
                }
                
            }
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String route = req.getParameter("action");
        System.out.println("route = " + route);
        
        switch(route){
            case "delete"->{
                try{
                    int id = Integer.parseInt(req.getParameter("id"));
                    System.out.println("id:" + id);
                    
                    int result = BookDAO.eliminar(id);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write("{\"success\": true}");
                }catch(IOException | NumberFormatException e){
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write("{\"success\": false, \"message\": \"Error al borrar el registro.\"}");
                }
            }
            
            default->{
                System.out.println("error en parametro.");
            }
        }
    }
}




































