/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import javax.swing.JOptionPane;
import Entidad.Devoluciones;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author FERNANDO
 */
public class DevolucionesDao {
    /**
    public List<Devoluciones> listarDevoluciones() {
        List<Devoluciones> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = " FROM Devoluciones";
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
    **/

     public List<Object[]> listarDevoluciones() {
        List<Object[]> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = " FROM Devoluciones as d INNER JOIN d.cliente INNER JOIN d.libro";
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

    public void agregar(Devoluciones devolucion) {
        Session sesion = null;
        String nom=  devolucion.getCliente().getNombre();
        String estado = String.valueOf(devolucion.getComentario());
        
        if(!(nom.isEmpty()|| estado.isEmpty())){
            
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(devolucion);
            System.out.println("registrando devolucion");
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

    public void modificar(Devoluciones devolucion) {
        Session sesion = null;
         String nom=  devolucion.getCliente().getNombre();
        String estado = String.valueOf(devolucion.getComentario());
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(devolucion);
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

    public void eliminar(Devoluciones devolucion) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(devolucion);
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