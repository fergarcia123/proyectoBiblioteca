/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import javax.swing.JOptionPane;
import Entidad.Autor;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author FERNANDO
 */
public class AutorDao {
   
    public List<Autor> listarAutor() {
        List<Autor> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Autor";
        try {
            lista = sesion.createQuery(hql).list();
            System.out.println("listando datos -> ...");
            t.commit();
            sesion.close();
        } catch (Exception e) {
            System.out.println("error al listar -> "+e);
            t.rollback();
        }
        return lista;
    }

    public List<Autor> listarAutorActivos() {
        List<Autor> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Autor as a where a.estado>0";
        try {
            lista = sesion.createQuery(hql).list();
            System.out.println("listando datos -> ...");
            t.commit();
            sesion.close();
        } catch (Exception e) {
            System.out.println("error al listar -> "+e);
            t.rollback();
        }
        return lista;
    }

    
    
    public void agregar(Autor autor) {
        Session sesion = null;
        String nom=  autor.getNombreAutor();
        String estado = String.valueOf(autor.getEstado());
        
        if(!(nom.isEmpty()|| estado.isEmpty())){
            
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(autor);
            System.out.println("registrando autor");
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

    public void modificar(Autor autor) {
        Session sesion = null;
        
        String nom=  autor.getNombreAutor();
        String estado = String.valueOf(autor.getEstado());
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(autor);
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

    public void eliminar(Autor autor) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(autor);
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
