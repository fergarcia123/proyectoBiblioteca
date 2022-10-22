/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import javax.swing.JOptionPane;
import Entidad.Tipo;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author FERNANDO
 */
public class TipoDao {
    
    public List<Tipo> listarTipo() {
        List<Tipo> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Tipo";
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

    public List<Tipo> listarTipoActivo() {
        List<Tipo> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Tipo as t where t.estado>0";
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

   
    public void agregar(Tipo tipo) {
        Session sesion = null;
        String nom=  tipo.getTipo();
        String estado = String.valueOf(tipo.getEstado());
        
        if(!(nom.isEmpty()|| estado.isEmpty())){
            
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(tipo);
            System.out.println("registrando tipo");
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

    public void modificar(Tipo tipo) {
        Session sesion = null;
        
        String nom=  tipo.getTipo();
        String estado = String.valueOf(tipo.getEstado());
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(tipo);
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

    public void eliminar(Tipo tipo) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(tipo);
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

