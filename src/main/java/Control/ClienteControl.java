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
import Dao.ClienteDao;
import Entidad.Cliente;
import java.util.ArrayList;
import javax.faces.model.SelectItem;


/**
 *
 * @author FERNANDO
 */
@ManagedBean
@ViewScoped
public class ClienteControl {

    /**
     * Creates a new instance of ClienteControl
     */
    private List<Cliente> listaCliente;
    private Cliente cliente;
    public List<SelectItem> selectCliente;
    public ClienteControl() {
        cliente = new Cliente();
    }

    public List<SelectItem> getSelectCliente() {
        this.selectCliente = new ArrayList<SelectItem>();
        ClienteDao cated = new ClienteDao();
        List<Cliente> ls = cated.listarClienteActivo();
        selectCliente.clear();
        for (Cliente opcion : ls) {
            SelectItem Item = new SelectItem(opcion.getIdCliente(), 
                    opcion.getNombre() + " " + opcion.getApellido());
            this.selectCliente.add(Item);
        }
        return selectCliente;
    }


    
    public List<Cliente> getListaCliente() {
        ClienteDao ad = new ClienteDao();
        listaCliente = ad.listarCliente();
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void limpiarCliente() {
        cliente = new Cliente();
    }

    public void agregarCliente() {
        ClienteDao ad = new ClienteDao();
        ad.agregar(cliente);
    }

    public void modificarCliente() {
        ClienteDao ad = new ClienteDao();
        ad.modificar(cliente);
        limpiarCliente();
    }

    public void eliminarCliente() {
        ClienteDao ad = new ClienteDao();
        ad.eliminar(cliente);
        limpiarCliente();
    }
}
