package com.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

import org.primefaces.event.SelectEvent;

import com.dao.AlimentosDAO;
import com.dtos.AlimentoDTO;
import com.entities.Alimento;
import com.entities.Unidad;
import com.enums.TipoAlimento;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.AlimentosBean;
import com.servicios.UnidadBean;
import com.validaciones.ValidacionDatos;


@ManagedBean
@SessionScoped
public class AlimentoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public AlimentoMB() {
		if(alimento == null) {
			alimento = new AlimentoDTO();
		}
	}
	
	@EJB
	private AlimentosBean alimentosBean;
	
	@EJB
	private AlimentosDAO alimentosDAO;
	
	@EJB
	private UnidadBean unidadBean;
	
	private AlimentoDTO alimento = new AlimentoDTO();
	
	private Alimento unAlimento = new Alimento();
	
	private LinkedList<AlimentoDTO> alimentos = new LinkedList<AlimentoDTO>();
	
	private String nombre;
	
	private Long idAlimento;
	
	private BigDecimal cantidad;
	
	private BigDecimal costoUnidad;
	
	private Unidad unidad;
	
	private Alimento alimentoSeleccionado;

    public void onRowSelect( SelectEvent event_ )
    {
    }
    
	//Ingreso Alimento
	public String altaAlimento() throws ServiciosException, NamingException, SQLException {

		String mostrar = null;
		boolean alta = false;

		String nombre = alimento.getNombre().toUpperCase();	
		


		//Comprueba alimento no este en stock
		for (String s : comprobarNombre()) {
			if (nombre.equals(s)) {
				FacesMessage msg = new FacesMessage("El Alimento ya existe!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return mostrar;

			} 
		}
				
		if(ValidacionDatos.validarNombre(nombre)) {
			
		TipoAlimento tipo = TipoAlimento.valueOf(nombre);
						
		long idAlimento = (long) tipo.getNumero();
		
		long idUnidad = (long) tipo.getIdUnidad();
		
		Unidad unidad = unidadBean.obtenerUnidad(idUnidad);
		
		String cantidad = alimento.getCantidad().toString().trim();

		String costoUni = alimento.getCostoUnidad().toString().trim();
		
		Double cantidadAlimento = Double.parseDouble(cantidad);

		Double costoUnidad = Double.parseDouble(costoUni);

		BigDecimal costoUnit = BigDecimal.valueOf(costoUnidad);
		
		BigDecimal cantidadAlime = BigDecimal.valueOf(cantidadAlimento);

		// ALTA
		try {
					
			if(ValidacionDatos.validar(idAlimento, costoUnit, cantidadAlime)) {
				
			Alimento a = new Alimento(idAlimento, cantidadAlime, costoUnit, nombre, unidad);
			alimentosBean.alta(a);
			alta = true;

			if (alta) {
				FacesMessage msg = new FacesMessage("El Alimento se ha ingresado con éxito");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return mostrar;
				
			}
			
			if (!alta){
				FacesMessage msg = new FacesMessage("No se pudo ingresar el Alimento");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				limpiarFormulario();
				mostrar = "ingresoAlimento";
				return mostrar;

			}
		}

		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return mostrar;
	}
     
	//Actualizar Alimento
    public String actualizarAlimento() throws NamingException, ServiciosException {
    	
    	String mostrar = null;
    	
    	boolean alta = false;
    	
		long idAlimento = alimentoSeleccionado.getIdAlimento();
					
		BigDecimal costoUnit = alimentoSeleccionado.getCostoUnidad();
		
		BigDecimal cantidadAlime = alimentoSeleccionado.getCantidad();
		
		Alimento a = alimentosBean.obtenerAlimento(idAlimento);

		// NUEVOS ATRIBUTOS DE ALIMENTO
		try {
			
			if(ValidacionDatos.validarActualizacion(costoUnit, cantidadAlime)) {
			
			a.setCostoUnidad(costoUnit);
			a.setCantidad(cantidadAlime);
				
			alimentosBean.actualizar(a);
			alta = true;

			if (alta) {
				FacesMessage msg = new FacesMessage("El Alimento se ha actualizado con éxito");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return mostrar;
				
			} else {
				limpiarFormulario();
				mostrar = "modificarAlimento";
				return mostrar;

			}
		}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("No se pudo agregar el alimento!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		return mostrar;
    	
    }
    
    //Agregar Alimento
    public String agregarAlimento() {
		
		boolean alta = false;
		
		String mostrar = null;
		
		String nombre = (String) alimento.getNombre();
		
		BigDecimal cantidad = alimento.getCantidad();
		
		Alimento a = alimentosBean.obtenerPorNombre(nombre);
		
		
		try {
			if(ValidacionDatos.validarAgregar(cantidad))
			a.setCantidad(a.getCantidad().add(cantidad));
			
			alimentosBean.actualizar(a);
			
			alta = true;

			if (alta) {
				
				FacesMessage msg = new FacesMessage("Alimento agregado!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return mostrar;
				
			} 
			

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("No se pudo agregar el alimento!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		

	
		return mostrar;
		
	}

	//Obtiene todos los alimentos
	public List<Alimento> getAllAlimentos() throws MyException, SQLException {

		List<Alimento> todosAlimentos = new LinkedList<Alimento>();
		todosAlimentos = alimentosBean.obtenerTodos();

		return todosAlimentos;

	}
		
	//Redirige la pantalla a ingreso de alimentos
	public String ingresoAlimento() {
		return "ingresoAlimento";
	}

	//Cargo los campos del combo
	public List<SelectItem> getAllAlimentosLista() throws SQLException{

		   List<SelectItem> items = new ArrayList<SelectItem>();
		   List<Alimento> alimentosLista = alimentosBean.obtenerTodos();
		    for(Alimento a: alimentosLista){
		       items.add(new SelectItem(a.getNombre()));
		   }
		   return items;
		}
	
	
	//Limpiar el formulario para un nuevo ingreso
	public void limpiarFormulario() {
		this.alimento.setNombre(null);
		this.alimento.setCantidad(null);
		this.alimento.setCostoUnidad(null);
	}
	
	
	//Comprobar nombre
	private LinkedList<String> comprobarNombre() throws ServiciosException, NamingException, SQLException {

		// obtengo los nombres de la bd
		LinkedList<String> nombres = new LinkedList<>();

		for (Alimento a : alimentosBean.obtenerTodos()) {
			nombres.add(a.getNombre());
		}

		return nombres;
	}
	

	//GETTERS AND SETTERS
	public AlimentosBean getAlimentosBean() {
		return alimentosBean;
	}

	public void setAlimentosBean(AlimentosBean alimentosBean) {
		this.alimentosBean = alimentosBean;
	}

	public AlimentosDAO getAlimentosDAO() {
		return alimentosDAO;
	}

	public void setAlimentosDAO(AlimentosDAO alimentosDAO) {
		this.alimentosDAO = alimentosDAO;
	}

	public AlimentoDTO getAlimento() {
		return alimento;
	}

	public void setAlimento(AlimentoDTO alimento) {
		this.alimento = alimento;
	}

	public Alimento getUnAlimento() {
		return unAlimento;
	}

	public void setUnAlimento(Alimento unAlimento) {
		this.unAlimento = unAlimento;
	}

	public LinkedList<AlimentoDTO> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(LinkedList<AlimentoDTO> alimentos) {
		this.alimentos = alimentos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCostoUnidad() {
		return costoUnidad;
	}

	public void setCostoUnidad(BigDecimal costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Alimento getAlimentoSeleccionado() {
		return alimentoSeleccionado;
	}

	public void setAlimentoSeleccionado(Alimento alimentoSeleccionado) {
		this.alimentoSeleccionado = alimentoSeleccionado;
	}
	
}