package com.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

import com.dtos.PesoDTO;
import com.entities.Peso;
import com.entities.Ternero;
import com.enums.TipoRegistroPeso;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.PesoBean;
import com.servicios.TernerosBean;
import com.validaciones.ValidacionDatos;

@ManagedBean
@SessionScoped
public class PesoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	public PesoMB() {
		if (peso == null) {
			peso = new PesoDTO();
		}
	}

	@EJB
	private PesoBean pesoBean;

	@EJB
	private TernerosBean ternerosBean;

	private PesoDTO peso = new PesoDTO();

	private String tipoRegistro;

	private Date fechaRegistro;

	private BigDecimal pesoTernero;

	private Ternero terneroSeleccionado;

	private BigDecimal ganancia;

	public void onRowSelect(SelectEvent event_) {
	}

	// Metodo que permite el ingreso de un registro peso al ternero
	public String altaGananciaPeso() throws ServiciosException {

		String mostrar = null;

		// TERNERO
		Long idTernero = terneroSeleccionado.getIdTernero();

		Ternero ternero = new Ternero();

		ternero = ternerosBean.obtenerTernero(idTernero);

		double pesoNacimiento = ternero.getPesoNac().doubleValue();

		String tipoRegistro = peso.getTipoRegistro();

		Date fecha = (Date) peso.getFecha();

		BigDecimal peso2 = peso.getPeso();

		BigDecimal ganancia2 = peso.getGanancia();

		double peso = peso2.doubleValue();

		double ganancia = 0;

		try {

			if (ValidacionDatos.validarPeso(peso2, fecha)) {
				
				
				// Sino existe un registro en la tabla
				if (ternero.getListaRegPeso().size() < 1) {
					
					double pesoNac = ternero.getPesoNac().doubleValue();
					
					double difPeso1 = peso - pesoNac;

					BigDecimal ganancia4 = BigDecimal.valueOf(difPeso1);
									
					Peso p1 = new Peso(fecha, peso2, tipoRegistro, ternero, idTernero, ganancia4);
					pesoBean.alta(p1);

					FacesMessage msg = new FacesMessage(
							"La ganancia de peso es de " + ganancia4 + " . " + "Peso Registrado");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					
					if(ganancia4.intValueExact() <= 0) {
				        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El ternero no muestra ganancia de peso frente a registro anterior, se recomienda dar una revision inmediata", "ERROR"));
					}
				
				} 
				
				// Si existe un registro en la tabla
				if (ternero.getListaRegPeso().size() >= 1) {

					int ultimo = ternero.getListaRegPeso().size() - 1;
					Peso ultimo1 = ternero.getListaRegPeso().get(ultimo);
					
					double ultimoPeso = ultimo1.getPeso().doubleValue();
					
					double difPeso1 = peso - ultimoPeso;

					BigDecimal ganancia3 = BigDecimal.valueOf(difPeso1);

					Peso p = new Peso(fecha, peso2, tipoRegistro, ternero, idTernero, ganancia3);
					pesoBean.alta(p);

					FacesMessage msg2 = new FacesMessage("La ganancia de peso es de " + ganancia3 + "registrado");
					FacesContext.getCurrentInstance().addMessage(null, msg2);
					
					if(ganancia3.intValueExact() <= 0) {
				        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El ternero no muestra ganancia de peso frente a registro anterior, se recomienda dar una revision inmediata", "ERROR"));

					}
		
				}
				

		
			}

		} catch (Exception e) {

			FacesMessage msg = new FacesMessage("No se pudo completar el registro!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}

		return mostrar;

	}

	// Cargo los campos del combo tipoRegistro
	public TipoRegistroPeso[] getRegistroPeso() {
		return TipoRegistroPeso.values();
	}

	// Obtengo todos los terneros para mostar en la tabla
	public List<Ternero> getAllTerneros() throws MyException, ServiciosException {

		List<Ternero> todosTerneros = new LinkedList<Ternero>();
		todosTerneros = ternerosBean.obtenerTodos();

		return todosTerneros;

	}

	// Obtengo todos los registros de peso para mostrar en la tabla
	public List<Peso> getAllPeso() throws MyException, ServiciosException {

		List<Peso> pesos = new LinkedList<Peso>();
		pesos = pesoBean.obtenerTodos();

		return pesos;

	}

	public PesoBean getPesoBean() {
		return pesoBean;
	}

	public void setPesoBean(PesoBean pesoBean) {
		this.pesoBean = pesoBean;
	}

	public TernerosBean getTernerosBean() {
		return ternerosBean;
	}

	public void setTernerosBean(TernerosBean ternerosBean) {
		this.ternerosBean = ternerosBean;
	}

	public PesoDTO getPeso() {
		return peso;
	}

	public void setPeso(PesoDTO peso) {
		this.peso = peso;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public BigDecimal getPesoTernero() {
		return pesoTernero;
	}

	public void setPesoTernero(BigDecimal pesoTernero) {
		this.pesoTernero = pesoTernero;
	}

	public Ternero getTerneroSeleccionado() {
		return terneroSeleccionado;
	}

	public void setTerneroSeleccionado(Ternero terneroSeleccionado) {
		this.terneroSeleccionado = terneroSeleccionado;
	}

}
