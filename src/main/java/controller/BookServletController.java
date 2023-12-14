package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.BookDAO;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import model.Book;

@WebServlet("/books")
public class BookServletController extends HttpServlet{
    
    List<Book> bookList = new ArrayList();
    ObjectMapper mapper = new ObjectMapper();
    
    @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
            String route = req.getParameter("action");

            switch (route){
                case "getAll"->{
                    res.setContentType("application/json; charset=UTF-8");
                    bookList = BookDAO.seleccionar();

                    for(Book book: bookList){
                        byte[] imagenBytes = book.getImagen();
                        String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
                        book.setImagenBase64(imagenBase64);
                    }

                    mapper.writeValue(res.getWriter(), bookList);
                }
                case "getById"->{
                   int id = Integer.parseInt(req.getParameter("id"));

                   Book bookDetails = BookDAO.seleccionarPorId(id);

                   res.setContentType("application/json");

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
    protected void doPost(HttpServletRequest req, HttpServletResponse res){
        
        
    }
    
     @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res){
        
    }
}





































