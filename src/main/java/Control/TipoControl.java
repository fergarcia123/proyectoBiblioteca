/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Dao.AutorDao;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import Dao.TipoDao;
import Entidad.Autor;
import Entidad.Tipo;
import java.util.ArrayList;
import javax.faces.model.SelectItem;


/**
 *
 * @author FERNANDO
 */
@ManagedBean
@ViewScoped
public class TipoControl {

    /**
     * Creates a new instance of TipoControl
     */
    private List<Tipo> listaTipo;
    private Tipo tipo;
    public List<SelectItem> selectTipo;
    public TipoControl() {
        tipo = new Tipo();
    }

    
    
    public List<SelectItem> getSelectTipo() {
        this.selectTipo = new ArrayList<SelectItem>();
        TipoDao cated = new TipoDao();
        List<Tipo> ls = cated.listarTipoActivo();
        selectTipo.clear();
        for (Tipo opcion : ls) {
            SelectItem Item = new SelectItem(opcion.getIdTipo(),
                    opcion.getTipo());
            this.selectTipo.add(Item);
        }
        return selectTipo;
    }

    
    
    
    public List<Tipo> getListaTipo() {
        TipoDao ad = new TipoDao();
        listaTipo = ad.listarTipo();
        return listaTipo;
    }

    public void setListaTipo(List<Tipo> listaTipo) {
        this.listaTipo = listaTipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void limpiarTipo() {
        tipo = new Tipo();
    }

    public void agregarTipo() {
        TipoDao ad = new TipoDao();
        ad.agregar(tipo);
    }

    public void modificarTipo() {
        TipoDao ad = new TipoDao();
        ad.modificar(tipo);
        limpiarTipo();
    }

    public void eliminarTipo() {
        TipoDao ad = new TipoDao();
        ad.eliminar(tipo);
        limpiarTipo();
    }
}
