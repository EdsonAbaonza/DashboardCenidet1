package net.javaguide.springboot.springsecurity.PDFS;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import net.javaguides.springboot.springsecurity.model.Cursos;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.model.Publicaciones;
import net.javaguides.springboot.springsecurity.model.Tesis;
import net.javaguides.springboot.springsecurity.model.Utiles;



public class PDFExporter {
	

	@Autowired
    ApplicationContext context;
	
	private List<Profesor> listProfesors;
	private List<Tesis> listTesis;
	private List<Publicaciones> listPublicaciones;
	
    
    public PDFExporter(List<Profesor> listProfesors, List<Tesis> listTesis, List<Publicaciones> listPublicaciones) {
        this.listProfesors = listProfesors;
        this.listTesis = listTesis;
        this.listPublicaciones = listPublicaciones;
    }
    
    //Tabla Profesores
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(114, 178, 217));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        com.lowagie.text.Font encabezado = FontFactory.getFont("Helvetica",14,Color.BLACK);
         
         
        cell.setPhrase(new Phrase("Numero de Control", encabezado));
        table.addCell(cell);
         
         
        cell.setPhrase(new Phrase("Nombre", encabezado));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Apellido Paterno", encabezado));
        table.addCell(cell);    
        
        cell.setPhrase(new Phrase("Apelllido Materno", encabezado));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("Linea", encabezado));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Status", encabezado));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("SNI", encabezado));
        table.addCell(cell);
    }
     
    private PdfPTable writeTableData(PdfPTable table) {
        for (Profesor user : listProfesors) {
            table.addCell(user.getNumero_control());
            table.addCell(user.getNombre());
            table.addCell(user.getApellido_paterno());
            table.addCell(user.getApellido_materno());
            table.addCell(user.getLinea());
            table.addCell(user.getStatus());
            table.addCell(user.getSni());
        }
        return table;
    }
    
    //Tabla Tesis
    private void writeTableHeader1(PdfPTable table1) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(114, 178, 217));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        com.lowagie.text.Font encabezado = FontFactory.getFont("Helvetica",14,Color.BLACK);
         
        cell.setPhrase(new Phrase("Titulo",encabezado));
        table1.addCell(cell);
         
        cell.setPhrase(new Phrase("Fecha inicio",encabezado));
        table1.addCell(cell);
         
        cell.setPhrase(new Phrase("Fecha fin",encabezado));
        table1.addCell(cell);    
        
        cell.setPhrase(new Phrase("Status",encabezado));
        table1.addCell(cell); 
        
        cell.setPhrase(new Phrase("Alumno",encabezado));
        table1.addCell(cell);
        
        cell.setPhrase(new Phrase("Departamento",encabezado));
        table1.addCell(cell);
        
        cell.setPhrase(new Phrase("Linea",encabezado));
        table1.addCell(cell);
    }
    private PdfPTable writeTableData1(PdfPTable table1) {
        for (Tesis tesis : listTesis) {
            table1.addCell(tesis.getTitulo());
            table1.addCell(tesis.getFechainicio());
            table1.addCell(tesis.getFechaprobfin());
            table1.addCell(tesis.getStatus());
            table1.addCell(tesis.getAlumno());
            table1.addCell(tesis.getDepartamento().getNombredepartamento());
            table1.addCell(tesis.getLinea());
        }
        
        return table1;
    }
    
    //Tabla Publicaciones
    private void writeTableHeader2(PdfPTable table2) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(114, 178, 217));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        com.lowagie.text.Font encabezado = FontFactory.getFont("Helvetica",14,Color.BLACK);
         
        cell.setPhrase(new Phrase("Año",encabezado));
        table2.addCell(cell);
         
        cell.setPhrase(new Phrase("Titulo",encabezado));
        table2.addCell(cell);   
        
        cell.setPhrase(new Phrase("Indice",encabezado));
        table2.addCell(cell); 
        
        cell.setPhrase(new Phrase("Colaborador(es)",encabezado));
        table2.addCell(cell); 
        
        cell.setPhrase(new Phrase("Resumen",encabezado));
        table2.addCell(cell); 
        
        
        
    }
    private PdfPTable writeTableData2(PdfPTable table2) {
        for (Publicaciones publicaciones : listPublicaciones) {
            table2.addCell(publicaciones.getAno());
            table2.addCell(publicaciones.getTitulo());
            table2.addCell(publicaciones.getIndice());
            table2.addCell(publicaciones.getColaboradores());
            table2.addCell(publicaciones.getResumen());
        }
        return table2;
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document, response.getOutputStream());
        Image image = Image.getInstance("src/main/resources/static/img//Logo TecNM Horizontal.png");
        image.scaleAbsolute(120, 80);
        image.setAbsolutePosition(100, 510);
        Image image2 = Image.getInstance("src/main/resources/static/img//CENIDET.png");
        image2.scaleAbsolute(120, 80);
        image2.setAbsolutePosition(600, 510);
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(60, 60, 100, 20);
        document.setPageCount(0);
        document.open();
        document.add(image);
        document.add(image2);
        
        
        
        
        
        PdfPTable tablaProfesor = new PdfPTable(1);
        PdfPCell celda = null;
        com.lowagie.text.Font titulo = FontFactory.getFont("Helvetica",30,Color.BLACK);
       
        celda = new PdfPCell(new Phrase("Datos Profesor", titulo));
        celda.setSpaceCharRatio(0);
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(255, 255, 255));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(5);
       
        
        tablaProfesor.addCell(celda);
        tablaProfesor.setSpacingAfter(15);
        
        // Encabezados tabla profesores
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.0f, 3.5f, 3.0f, 3.0f, 2.0f,3.0f,3.0f});
        table.setSpacingBefore(10);
        
     // Encabezados tabla tesis
        PdfPTable tablaTesis = new PdfPTable(1);
        PdfPCell celda1 = null;
        celda1 = new PdfPCell(new Phrase("Tesis", titulo));
        celda1.setBorder(0);
        celda1.setBackgroundColor(new Color(255, 255, 255));
        celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda1.setVerticalAlignment(Element.ALIGN_CENTER);
        tablaTesis.addCell(celda1);
        tablaTesis.setSpacingAfter(15);
        tablaTesis.setSpacingBefore(30);
        
        
        PdfPTable table1 = new PdfPTable(7);
        table1.setWidthPercentage(100f);
        table1.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 2.0f,3.0f,3.0f});
        table1.setSpacingBefore(10);
        
     // Encabezados tabla publicaciones
        PdfPTable tablaPublicaciones = new PdfPTable(1);
        PdfPCell celda2 = null;
        celda2 = new PdfPCell(new Phrase("Publicaciones", titulo));
        celda2.setBorder(0);
        celda2.setBackgroundColor(new Color(255, 255, 255));
        celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda2.setVerticalAlignment(Element.ALIGN_CENTER);
        tablaPublicaciones.addCell(celda2);
        tablaPublicaciones.setSpacingAfter(15);
        tablaPublicaciones.setSpacingBefore(30);
        
        
        PdfPTable table2 = new PdfPTable(5);
        table2.setWidthPercentage(100f);
        table2.setWidths(new float[] {1.5f, 3.5f, 2.0f, 3.0f, 3.0f});
        table2.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableHeader1(table1);
        writeTableHeader2(table2);
        
         
        document.add(tablaProfesor);
        document.add(writeTableData(table));
        
        document.add(tablaTesis);
        document.add(writeTableData1(table1));
        
        document.add(tablaPublicaciones);
        document.add(writeTableData2(table2));
        
        document.close();
         
    }
}

