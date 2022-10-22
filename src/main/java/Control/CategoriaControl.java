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
import Dao.CategoriaDao;
import Dao.ClienteDao;
import Entidad.Categoria;
import Entidad.Cliente;
import java.util.ArrayList;
import javax.faces.model.SelectItem;


/**
 *
 * @author FERNANDO
 */
@ManagedBean
@ViewScoped
public class CategoriaControl {

    /**
     * Creates a new instance of CategoriaControl
     */
    private List<Categoria> listaCategoria;
    private Categoria categoria;

    public List<SelectItem> selectCategoria;
    public CategoriaControl() {
        categoria = new Categoria();
    }

    public List<SelectItem> getSelectCategoria() {
        this.selectCategoria = new ArrayList<SelectItem>();
        CategoriaDao cated = new CategoriaDao();
        List<Categoria> ls = cated.listarCategoriasActivas();
        selectCategoria.clear();
        for (Categoria opcion : ls) {
            SelectItem categoriaItem = new SelectItem(opcion.getIdCategoria(),
                    opcion.getCategoria());
            this.selectCategoria.add(categoriaItem);
        }
        return selectCategoria;
    }
    
    public List<Categoria> getListaCategoria() {
        CategoriaDao ad = new CategoriaDao();
        listaCategoria = ad.listarCategoria();
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void limpiarCategoria() {
        categoria = new Categoria();
    }

    public void agregarCategoria() {
        CategoriaDao ad = new CategoriaDao();
        ad.agregar(categoria);
    }

    public void modificarCategoria() {
        CategoriaDao ad = new CategoriaDao();
        ad.modificar(categoria);
        limpiarCategoria();
    }

    public void eliminarCategoria() {
        CategoriaDao ad = new CategoriaDao();
        ad.eliminar(categoria);
        limpiarCategoria();
    }
}
