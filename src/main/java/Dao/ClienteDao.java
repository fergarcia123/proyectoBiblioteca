/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import javax.swing.JOptionPane;
import Entidad.Cliente;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author FERNANDO
 */
public class ClienteDao {

    public List<Cliente> listarCliente() {
        List<Cliente> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Cliente";
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

        public List<Cliente> listarClienteActivo() {
        List<Cliente> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Cliente as c where c.estado>0";
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

    
    public void agregar(Cliente cliente) {
        Session sesion = null;
        String nom=  cliente.getNombre();
        String estado = String.valueOf(cliente.getEstado());
        String dpi = cliente.getDpi();
        if(!(nom.isEmpty()|| estado.isEmpty() || dpi.isEmpty())){
            
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(cliente);
            System.out.println("registrando cliente");
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

    public void modificar(Cliente cliente) {
        Session sesion = null;
        
        String nom=  cliente.getNombre();
        String estado = String.valueOf(cliente.getEstado());
        String dpi = cliente.getDpi();
        if(!(nom.isEmpty()|| estado.isEmpty() || dpi.isEmpty())){
            
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(cliente);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }}else{
            System.out.println("No se permite formulario vacío");
        }
    }

    public void eliminar(Cliente cliente) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(cliente);
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
