/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import Dao.LibroDao;
import Entidad.Autor;
import Entidad.Categoria;
import Entidad.Libro;
import Entidad.ListadoLibro;
import Entidad.Tipo;
import java.util.ArrayList;
import javax.faces.model.SelectItem;


/**
 *
 * @author FERNANDO
 */
@ManagedBean
@ViewScoped
public class LibroControl {

    /**
     * Creates a new instance of LibroControl
     */
    private List<ListadoLibro> listaLibro;
    private Libro libro;
    public ListadoLibro libroNuevo = new ListadoLibro();
    public List<SelectItem> selectLibro;
    public LibroControl() {
        libro = new Libro();
    }

    
    public List<SelectItem> getSelectLibro() {
        this.selectLibro = new ArrayList<SelectItem>();
        LibroDao cated = new LibroDao();
        List<Libro> ls = cated.listarLibrosActivos();
        selectLibro.clear();
        for (Libro opcion : ls) {
            SelectItem Item = new SelectItem(opcion.getIdLibro(),
                    opcion.getTitulo());
            this.selectLibro.add(Item);
        }
        return selectLibro;
    }
    
    
    public List<ListadoLibro> getListaLibro() {
        LibroDao ad = new LibroDao();
        List<Object[]> registros = ad.listarLibro();
        ListadoLibro listado;
        listaLibro = new ArrayList<ListadoLibro>();
        for (Object[] datos : registros) {
                Libro l = (Libro) datos[0];
                Categoria c = (Categoria) datos[1];
                Autor aut = (Autor) datos[2];
                Tipo tip = (Tipo) datos[3];
                String tit= l.getTitulo();
                System.out.println("libro: " + l.getTitulo());
                System.out.println("categoria: " + c.getIdCategoria());
                System.out.println("categoria ti: " + c.getCategoria());
                System.out.println("tupo: " + tip.getTipo());
                System.out.println("idtipo: " + tip.getIdTipo());
                System.out.println("autor: " + aut.getNombreAutor());
                System.out.println("idautor: " + aut.getIdAutor());
                
                listado = new ListadoLibro();
                listado.setAutor(aut.getNombreAutor());
                listado.setIdAutor(aut.getIdAutor());
                listado.setCategoria(c.getCategoria());
                listado.setIdCategoria(c.getIdCategoria());
                listado.setIdTipo(tip.getIdTipo());
                
                listado.setEstado(l.getEstado());
                listado.setIdLibro(l.getIdLibro());
                listado.setIsbn(l.getIsbn());
                listado.setTipo(tip.getTipo());
                listado.setTitulo(tit);
                listaLibro.add(listado);   
            }
        return listaLibro;
    }

    public void setListaLibro(List<ListadoLibro> listaLibro) {
        this.listaLibro = listaLibro;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void limpiarLibro() {
        libro = new Libro();
    }

    //listado nuevo
        public void agregarLibroNuevo() {
            
        LibroDao ad = new LibroDao();
        Autor autor = new Autor();
        Tipo tipo = new Tipo();
        Categoria categ = new Categoria();
        autor.setIdAutor(libroNuevo.getIdAutor());
        tipo.setIdTipo(libroNuevo.getIdTipo());
        categ.setIdCategoria(libroNuevo.getIdCategoria());
        //paso a libro
        libro.setAutor(autor);
        libro.setCategoria(categ);
        libro.setTipo(tipo);
            System.out.println("debtro de metodo gura -> "+libro.getAutor());
            System.out.println("dentron meteof -> "+tipo.getIdTipo());
        ad.agregar(libro);
    }

    public void modificarLibroNuevo() {
        LibroDao ad = new LibroDao();
        ad.modificar(libro);
        limpiarLibro();
    }

    public void eliminarLibroNuevo() {
        LibroDao ad = new LibroDao();
        ad.eliminar(libro);
        limpiarLibro();
    }

    
    
    
    
    
    
    
    //listado viejos
    public void agregarLibro() {
        LibroDao ad = new LibroDao();
        ad.agregar(libro);
    }

    public void modificarLibro() {
        LibroDao ad = new LibroDao();
        ad.modificar(libro);
        limpiarLibro();
    }

    public void eliminarLibro() {
        LibroDao ad = new LibroDao();
        ad.eliminar(libro);
        limpiarLibro();
    }

    public ListadoLibro getLibroNuevo() {
        return libroNuevo;
    }

    public void setLibroNuevo(ListadoLibro libroNuevo) {
        this.libroNuevo = libroNuevo;
    }
}
