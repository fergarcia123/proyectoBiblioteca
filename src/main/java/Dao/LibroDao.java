/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidad.Autor;
import Entidad.Categoria;
import java.util.List;
import javax.swing.JOptionPane;
import Entidad.Libro;
import Entidad.Tipo;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author FERNANDO
 */
public class LibroDao {
    
     public List<Libro> listarLibros() {
        List<Libro> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        
        String hql = "FROM Libro";
        try {
            lista = sesion.createQuery(hql).list();
            System.out.println("listando datos -> ...");
        
            t.commit();
            sesion.close();
        } catch (Exception e) {
            System.out.println("error al listar -> " + e);
            t.rollback();
        }
        return lista;
    }
    
      public List<Libro> listarLibrosActivos() {
        List<Libro> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        
        String hql = "FROM Libro as lib where lib.estado>0";
        try {
            lista = sesion.createQuery(hql).list();
            System.out.println("listando datos -> ...");
        
            t.commit();
            sesion.close();
        } catch (Exception e) {
            System.out.println("error al listar -> " + e);
            t.rollback();
        }
        return lista;
    }
    
     
    public List<Object[]> listarLibro() {
        List<Object[]> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        //     String hql = "FROM Libro";
        String hql = "FROM Libro as lib INNER JOIN lib.categoria INNER JOIN lib.autor INNER JOIN lib.tipo";
        try {
            lista = sesion.createQuery(hql).list();
            System.out.println("listando datos -> ...");
            /**for (Object[] datos : lista) {

                Libro l = (Libro) datos[0];
                Categoria c = (Categoria) datos[1];
                Autor aut = (Autor) datos[2];
                Tipo tip = (Tipo) datos[3];
                System.out.println("libro: " + l.getTitulo());
                System.out.println("categoria: " + c.getIdCategoria());
                System.out.println("categoria ti: " + c.getCategoria());
                System.out.println("tupo: " + tip.getTipo());
                System.out.println("idtipo: " + tip.getIdTipo());
                System.out.println("autor: " + aut.getNombreAutor());
                System.out.println("idautor: " + aut.getIdAutor());
                 
            }**/
            t.commit();
            sesion.close();
        } catch (Exception e) {
            System.out.println("error al listar -> " + e);
            t.rollback();
        }
        return lista;
    }

    public void agregar(Libro libro) {
        Session sesion = null;
        String nom=  libro.getTitulo();
        String estado = String.valueOf(libro.getEstado());
        
        if(!(nom.isEmpty()|| estado.isEmpty())){
            
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(libro);
            System.out.println("registrando libro");
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
            System.out.println("Error al guardar ---> "+e);
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        }else{
            System.out.println("Error- NO SE PUEDE GUARDAR UN FORMULARIO VACÍO-----");
            //JOptionPane.showMessageDialog(null, "Error","El formulario esta vacío",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificar(Libro libro) {
        Session sesion = null;
        
        String nom=  libro.getTitulo();
        String estado = String.valueOf(libro.getEstado());
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(libro);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public void eliminar(Libro libro) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(libro);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }
}
