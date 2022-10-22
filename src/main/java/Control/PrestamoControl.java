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
import Dao.PrestamoDao;
import Entidad.Cliente;
import Entidad.Libro;
import Entidad.ListadoPrestamo;
import Entidad.Prestamo;
import java.util.ArrayList;


/**
 *
 * @author FERNANDO
 */
@ManagedBean
@ViewScoped
public class PrestamoControl {

    /**
     * Creates a new instance of PrestamoControl
     */
    private List<ListadoPrestamo> listaPrestamo;
    private Prestamo prestamo;
    
    private ListadoPrestamo prestamoNuevo = new ListadoPrestamo();

    public PrestamoControl() {
        prestamo = new Prestamo();
    }

    public List<ListadoPrestamo> getListaPrestamo() {
        PrestamoDao ad = new PrestamoDao();
         List<Object[]> registros = ad.listarPrestamo();
        ListadoPrestamo listado;
        listaPrestamo = new ArrayList<ListadoPrestamo>();
        for (Object[] datos : registros) {
                Prestamo prst = (Prestamo) datos[0];
                Cliente cli = (Cliente) datos[1];
                Libro lib = (Libro) datos[2];
                String tit = lib.getTitulo();

                listado = new ListadoPrestamo();
                listado.setApellidoCliente(cli.getApellido());
                listado.setDpiCliente(cli.getDpi());
                listado.setEstadoPrestamo(prst.getEstado());
                listado.setFechaDevolucion(prst.getFechaDevolucion());
                listado.setFechaPrestado(prst.getFechaPrestado());
                listado.setIdCliente(cli.getIdCliente());
                listado.setIdLibro(lib.getIdLibro());
                listado.setIdPrestamo(prst.getIdPrestamo());
                listado.setIsbnLibro(lib.getIsbn());
                listado.setNombreCliente(cli.getNombre());
                listado.setTelefonoCliente(cli.getTelefono());
                listado.setTituloLibro(tit);
                listaPrestamo.add(listado);   
            }
        
        return listaPrestamo;
    }

    public void setListaPrestamo(List<ListadoPrestamo> listaPrestamo) {
        this.listaPrestamo = listaPrestamo;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public void limpiarPrestamo() {
        prestamo = new Prestamo();
    }

    public void agregarPrestamo() {
        PrestamoDao ad = new PrestamoDao();
        Cliente cliente = new Cliente();
        Libro libro = new Libro();
        cliente.setApellido(prestamoNuevo.getApellidoCliente());
        cliente.setNombre(prestamoNuevo.getNombreCliente());
        cliente.setTelefono(prestamoNuevo.getTelefonoCliente());
        cliente.setIdCliente(prestamoNuevo.getIdCliente());
        libro.setTitulo(prestamoNuevo.getTituloLibro());
        libro.setIsbn(prestamoNuevo.getIsbnLibro());
        libro.setIdLibro(prestamoNuevo.getIdLibro());
        
        prestamo.setCliente(cliente);
        prestamo.setLibro(libro);
        
        
        ad.agregar(prestamo);
    }

    public void modificarPrestamo() {
        
        PrestamoDao ad = new PrestamoDao();
        Cliente cliente = new Cliente();
        Libro libro = new Libro();
        cliente.setApellido(prestamoNuevo.getApellidoCliente());
        cliente.setNombre(prestamoNuevo.getNombreCliente());
        cliente.setTelefono(prestamoNuevo.getTelefonoCliente());
        cliente.setIdCliente(prestamoNuevo.getIdCliente());
        libro.setTitulo(prestamoNuevo.getTituloLibro());
        libro.setIsbn(prestamoNuevo.getIsbnLibro());
        libro.setIdLibro(prestamoNuevo.getIdLibro());
        
        prestamo.setCliente(cliente);
        prestamo.setLibro(libro);
        
        
        ad.modificar(prestamo);
        limpiarPrestamo();
    }

    public void eliminarPrestamo() {
        PrestamoDao ad = new PrestamoDao();
        ad.eliminar(prestamo);
        limpiarPrestamo();
    }

    public ListadoPrestamo getPrestamoNuevo() {
        return prestamoNuevo;
    }

    public void setPrestamoNuevo(ListadoPrestamo prestamoNuevo) {
        this.prestamoNuevo = prestamoNuevo;
    }
}
