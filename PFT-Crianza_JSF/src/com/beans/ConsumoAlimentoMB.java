package com.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import org.primefaces.event.SelectEvent;

import com.dtos.AlimentoDTO;
import com.dtos.ConsumoAlimentoDTO;
import com.entities.Alimento;
import com.entities.ConsumoAlimento;
import com.entities.Ternero;
import com.entities.Unidad;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.AlimentosBean;
import com.servicios.ConsumoAlimentosBean;
import com.servicios.TernerosBean;
import com.servicios.UnidadBean;
import com.validaciones.ValidacionDatos;

@ManagedBean
@SessionScoped
public class ConsumoAlimentoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	public ConsumoAlimentoMB() {
		if (consumoAlimentos == null) {
			consumoAlimentos = new ConsumoAlimentoDTO();
		}
	}

	@EJB
	private AlimentosBean alimentosBean;

	@EJB
	private ConsumoAlimentosBean consumoAlimentosBean;

	@EJB
	private TernerosBean ternerosBean;

	@EJB
	private UnidadBean unidadBean;

	private ConsumoAlimentoDTO consumoAlimentos = new ConsumoAlimentoDTO();

	private Long idAlimento;

	private Long idTernero;

	private Unidad unidad;

	private Ternero ternero;

	private AlimentoDTO alimento = new AlimentoDTO();

	private BigDecimal cantidadAlimento;

	private Alimento alimentoSeleccionado;

	private Ternero terneroSeleccionado;


	public void onRowSelect(SelectEvent event_) {
	}
	
	//Redirige la pantalla a agregar alimento
	public String agregarAlimento() {
		return "agregarAlimento";
	}
	
	//Redirige la pantalla a nuevo ternero
	public String altaTernero() {
		return "ingresoTernero";
	}

	//Ingreso Consumo Alimento
	public String altaConsumoAlimento() throws NamingException, ServiciosException {

		String mostrar = null;

		boolean alta = false;

		String nombre = (String) alimentoSeleccionado.getNombre();

		BigDecimal cantidad = consumoAlimentos.getCantidad();

		Date fecha = (Date) consumoAlimentos.getFecha();

		long idAlimento = alimentoSeleccionado.getIdAlimento();

		Alimento alimento = new Alimento();

		alimento = alimentosBean.obtenerAlimento(idAlimento);

		long idUnidad = alimento.getUnidad().getIdUnidad();

		Unidad unidad = unidadBean.obtenerUnidad(idUnidad);

		long idTernero = terneroSeleccionado.getIdTernero();

		Ternero ternero = new Ternero();

		try {
			ternero = ternerosBean.obtenerTernero(idTernero);

		} catch (ServiciosException ex) {
			ex.printStackTrace();
		}

		BigDecimal stockAlimento = alimento.getCantidad();

		double cantidadAlimento = cantidad.doubleValue();

		double d = stockAlimento.doubleValue();

		Double cantidadRestante = d - cantidadAlimento;

		if (cantidadAlimento > d) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
					"Solamente tienes " + stockAlimento + " de " + nombre + " para actualizar!", "System Error"));

			alta = false;
		} else {
			alta = true;
		}

		BigDecimal c1 = BigDecimal.valueOf(cantidadRestante);

		// NUEVOS ATRIBUTOS DE ALIMENTO
		try {

			if (ValidacionDatos.validarConsumoAlimento(cantidad, fecha) && alta) {
				ConsumoAlimento c = new ConsumoAlimento(cantidad, fecha, alimento, unidad, ternero);
				consumoAlimentosBean.alta(c);
				alimento.setCantidad(c1);
				alimentosBean.actualizar(alimento);

				alta = true;

				if (alta) {
					
					if (cantidadRestante == 0)

					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Has agotado el stock de " + nombre + "!", "Advertencia!"));
						FacesMessage msg = new FacesMessage("Consumo registrado.");
						FacesContext.getCurrentInstance().addMessage(null, msg);

					} else {
						FacesMessage msg = new FacesMessage("Consumo registrado.");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						
					}

					return mostrar;

				} else {
					limpiarFormulario();
					mostrar = "consumoAlimento";
					return mostrar;

				}

			}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("No se pudo completar el registro!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}


		return mostrar;

	}

	public void limpiarFormulario() {
		this.consumoAlimentos.setCantidad(null);
		this.consumoAlimentos.setFecha(null);

	}

	public List<Alimento> getAllAlimentos() throws MyException, SQLException {

		List<Alimento> todosAlimentos = new LinkedList<Alimento>();
		todosAlimentos = alimentosBean.obtenerTodos();

		return todosAlimentos;

	}

	public List<Ternero> getAllTerneros() throws MyException, ServiciosException {

		List<Ternero> todosTerneros = new LinkedList<Ternero>();
		todosTerneros = ternerosBean.obtenerTodos();

		return todosTerneros;

	}
	
	public List<ConsumoAlimento> getAllEventosClinicos() throws MyException, ServiciosException {

		List<ConsumoAlimento> consumoAlimentos = new LinkedList<ConsumoAlimento>();
		consumoAlimentos = consumoAlimentosBean.obtenerTodos();

		return consumoAlimentos;

	}

	// GETTERS AND SETTERS
	public AlimentosBean getAlimentosBean() {
		return alimentosBean;
	}

	public void setAlimentosBean(AlimentosBean alimentosBean) {
		this.alimentosBean = alimentosBean;
	}

	public ConsumoAlimentosBean getConsumoAlimentosBean() {
		return consumoAlimentosBean;
	}

	public void setConsumoAlimentosBean(ConsumoAlimentosBean consumoAlimentosBean) {
		this.consumoAlimentosBean = consumoAlimentosBean;
	}

	public TernerosBean getTernerosBean() {
		return ternerosBean;
	}

	public void setTernerosBean(TernerosBean ternerosBean) {
		this.ternerosBean = ternerosBean;
	}

	public UnidadBean getUnidadBean() {
		return unidadBean;
	}

	public void setUnidadBean(UnidadBean unidadBean) {
		this.unidadBean = unidadBean;
	}

	public ConsumoAlimentoDTO getConsumoAlimentos() {
		return consumoAlimentos;
	}

	public void setConsumoAlimentos(ConsumoAlimentoDTO consumoAlimentos) {
		this.consumoAlimentos = consumoAlimentos;
	}

	public Long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public Long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(Long idTernero) {
		this.idTernero = idTernero;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Ternero getTernero() {
		return ternero;
	}

	public void setTernero(Ternero ternero) {
		this.ternero = ternero;
	}

	public AlimentoDTO getAlimento() {
		return alimento;
	}

	public void setAlimento(AlimentoDTO alimento) {
		this.alimento = alimento;
	}

	public BigDecimal getCantidadAlimento() {
		return cantidadAlimento;
	}

	public void setCantidadAlimento(BigDecimal cantidadAlimento) {
		this.cantidadAlimento = cantidadAlimento;
	}

	public Alimento getAlimentoSeleccionado() {
		return alimentoSeleccionado;
	}

	public void setAlimentoSeleccionado(Alimento alimentoSeleccionado) {
		this.alimentoSeleccionado = alimentoSeleccionado;
	}

	public Ternero getTerneroSeleccionado() {
		return terneroSeleccionado;
	}

	public void setTerneroSeleccionado(Ternero terneroSeleccionado) {
		this.terneroSeleccionado = terneroSeleccionado;
	}

}
