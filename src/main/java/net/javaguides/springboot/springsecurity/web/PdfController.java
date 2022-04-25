package net.javaguides.springboot.springsecurity.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lowagie.text.DocumentException;

import net.javaguide.springboot.springsecurity.PDFS.PDFExporter;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.model.Publicaciones;
import net.javaguides.springboot.springsecurity.model.Tesis;
import net.javaguides.springboot.springsecurity.repository.CursosRepository;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.repository.PublicacionesRepository;
import net.javaguides.springboot.springsecurity.repository.TesisRepository;

@Controller
public class PdfController {
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private TesisRepository tesisRepository;
	
	@Autowired
	private PublicacionesRepository publicacionesRepository;
	
	@GetMapping("/profesor/export/pdf/{id}")
    public void exportToPDF(HttpServletResponse response, @PathVariable (value = "id") Long id) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=profesores_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Profesor> profesor = profesorRepository.findByid((long)id);
        List<Tesis> tesis = tesisRepository.findByProfesor_id((long)id);
        //List<Cursos> cursos = cursosRepository.findByProfesor_id((String)nombre);
        List<Publicaciones> publicaciones = publicacionesRepository.findByAutor_id((long)id);
         
        PDFExporter exporter = new PDFExporter(profesor, tesis, publicaciones);
        exporter.export(response);
       
    }

}
