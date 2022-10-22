/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import javax.swing.JOptionPane;
import Entidad.Categoria;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author FERNANDO
 */
public class CategoriaDao {
    
    
    public List<Categoria> listarCategoria() {
        List<Categoria> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Categoria";
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

    
    public List<Categoria> listarCategoriasActivas() {
        List<Categoria> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Categoria as c where c.estado>0";
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
    
    
    public void agregar(Categoria categoria) {
        Session sesion = null;
        String nom=  categoria.getCategoria();
        String estado = String.valueOf(categoria.getEstado());
        
        if(!(nom.isEmpty()|| estado.isEmpty())){
            
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(categoria);
            System.out.println("registrando categoria");
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

    public void modificar(Categoria categoria) {
        Session sesion = null;
        
        String nom=  categoria.getCategoria();
        String estado = String.valueOf(categoria.getEstado());
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(categoria);
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

    public void eliminar(Categoria categoria) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(categoria);
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
