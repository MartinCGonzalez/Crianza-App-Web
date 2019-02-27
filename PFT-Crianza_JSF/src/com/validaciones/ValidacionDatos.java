package com.validaciones;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.entities.Alimento;
import com.entities.Enfermedad;
import com.entities.Ternero;
import com.entities.Usuario;
import com.enums.TipoAlimento;
import com.exceptions.EnfermedadException;
import com.exceptions.FechaFuturoException;
import com.exceptions.FormatoFechaNoValido;
import com.exceptions.IdExistente;
import com.exceptions.LargoDecimalException;
import com.exceptions.LargoNumeroException;
import com.exceptions.NombreAlimentoException;
import com.exceptions.NroCaravanaExistente;
import com.exceptions.NumeroNegativoException;
import com.exceptions.ObservacionException;
import com.exceptions.ServiciosException;
import com.exceptions.TerneroNoVivoException;
import com.exceptions.UsuarioException;
import com.servicios.AlimentosBeanRemote;
import com.servicios.EnfermedadBeanRemote;
import com.servicios.TernerosBeanRemote;
import com.servicios.UsuarioBeanRemote;

public class ValidacionDatos {

	private ValidacionDatos() {

	}

	public static boolean validar(long idAlimento, BigDecimal costoUnit, BigDecimal cantidad) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionId(idAlimento) && validar.revisionCosto(costoUnit)
					&& validar.revisionCantidad(cantidad)) {
				salida = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	public static boolean validarActualizacion(BigDecimal costoUnit, BigDecimal cantidad) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionCantidadActualizacion(cantidad) && validar.revisionCosto(costoUnit)) {
				salida = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public static boolean validarAgregar(BigDecimal cantidad) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionCantidadActualizacion(cantidad)) {
				salida = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	public static boolean validarNombre(String nombre) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionNombreAlimento(nombre))
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	public static boolean validarConsumoAlimento(BigDecimal cantidad, Date date) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionCantidad(cantidad) && validar.revisionFecha(date))
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	public static boolean validarTernero(Long nroCaravana, BigDecimal cantidad, Date date) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionNroCaravana(nroCaravana) && validar.revisionPeso(cantidad)
					&& validar.revisionFecha(date))
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	public static boolean validarPeso(BigDecimal cantidad, Date date) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionFecha(date) && validar.revisionPeso(cantidad))
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	public static boolean validarEventoClinico(Date date, String obs) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionFecha(date) && validar.revisionObservacion(obs))
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	public static boolean validarEnfermedad(Long idEnfermedad) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionEnfermedad(idEnfermedad))
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	public static boolean validarEventoClinico2(Date fechaHasta, Date fechaDesde,
			String obs) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionFecha(fechaHasta)
					&& validar.revisionFecha(fechaDesde) && validar.revisionObservacion(obs))
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;

	}

	public static boolean validarUsuario(String usuario) {

		ValidacionDatos validar = new ValidacionDatos();
		boolean salida = false;
		try {
			if (validar.revisionUsuario(usuario))
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	// -----------------------
	// ---METODOS PRIMARIOS---
	// -----------------------

	private boolean revisionCosto(BigDecimal costoUnit)
			throws NumeroNegativoException, LargoDecimalException, LargoNumeroException {
		// Verifico sea mayor a cero
		if (!verificarPositivo(costoUnit)) {
			throw new NumeroNegativoException();
		}

		// Verifico no tenga mas de dos decimales
		if (verificarDecimal(costoUnit)) {
			throw new LargoDecimalException();
		}

		// Verifico el numero no tenga mas de 5 digitos
		if (verificarNumero(costoUnit)) {
			throw new LargoNumeroException();
		}
		return true;
	}

	private boolean revisionCantidad(BigDecimal cantidad)
			throws NumeroNegativoException, LargoDecimalException, LargoNumeroException {

		if (!verificarPositivo(cantidad)) {
			throw new NumeroNegativoException();
		}

		// Verifico no tenga mas de dos decimales
		if (verificarDecimal(cantidad)) {
			throw new LargoDecimalException();
		}

		// Verifico sea mayor a cero
		if (verificarNumero(cantidad)) {
			throw new LargoNumeroException();
		}
		
		return true;
	}

	private boolean revisionPeso(BigDecimal cantidad)
			throws NumeroNegativoException, LargoDecimalException, LargoNumeroException {

		if (!verificarPositivo(cantidad)) {
			throw new NumeroNegativoException();
		}

		// Verifico no tenga mas de dos decimales
		if (verificarDecimal(cantidad)) {
			throw new LargoDecimalException();
		}

		// Verifico sea mayor a cero
		if (verificarPeso(cantidad)) {
			throw new LargoNumeroException();
		}
		return true;
	}

	private boolean revisionCantidadActualizacion(BigDecimal cantidad)
			throws NumeroNegativoException, LargoDecimalException, LargoNumeroException {

		if (!verificarMayorCero(cantidad)) {
			throw new NumeroNegativoException();
		}
		// Verifico no tenga mas de dos decimales
		if (verificarDecimal(cantidad)) {
			throw new LargoDecimalException();
		}

		// Verifico sea mayor a cero
		if (verificarNumero(cantidad)) {
			throw new LargoNumeroException();
		}
		return true;

	}

	private boolean revisionNombreAlimento(String nombre) throws NombreAlimentoException {

		if (verificarNombre(nombre)) {
			throw new NombreAlimentoException();
		}

		return true;
	}

	private boolean revisionId(Long idAlimento) throws IdExistente {

		try {
			if (verificarId(idAlimento)) {
				throw new IdExistente();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private boolean revisionNroCaravana(Long nroCaravana) throws NroCaravanaExistente {

		try {
			if (verificarNroCaravanaTernero(nroCaravana)) {
				throw new NroCaravanaExistente();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private boolean revisionFecha(Date date) throws FechaFuturoException, FormatoFechaNoValido {

		if (verificarFecha(date)) {
			throw new FechaFuturoException();
		}

		if (verificarFormatoFecha(date)) {
			throw new FormatoFechaNoValido();
		}
		return true;
	}

	private boolean revisionEnfermedad(Long idEnfermedad)
			throws EnfermedadException, NamingException, ServiciosException {

		if (verificarEnfermedad(idEnfermedad)) {
			throw new EnfermedadException();
		}
		return true;
	}

	private boolean revisionTerneroVivo(Long idTernero)
			throws TerneroNoVivoException, NamingException, ServiciosException {

		if (verificarTerneroVivo(idTernero)) {
			throw new TerneroNoVivoException();
		}
		return true;
	}

	private boolean revisionObservacion(String obs) throws ObservacionException {

		if (verificarObservaciones(obs)) {
			throw new ObservacionException();
		}
		return true;
	}

	private boolean revisionUsuario(String usuario) throws UsuarioException, NamingException, ServiciosException {

		if (verificarUsuario(usuario)) {
			throw new UsuarioException();
		}

		return true;
	}

	// -------------------------
	// ---METODOS SECUNDARIOS---
	// -------------------------

	public static boolean verificarId(Long idAlimento) throws NamingException, ServiciosException {

		AlimentosBeanRemote alimentoBean = (AlimentosBeanRemote) InitialContext
				.doLookup("PFT-Crianza_EJB/AlimentosBean!com.servicios.AlimentosBeanRemote");

		// FUNCIONANDO
		boolean existe;

		Alimento alimento = alimentoBean.obtenerAlimento(idAlimento);

		if (alimento == null) {
			existe = false;
		} else {
			existe = true;
		}

		return existe;

	}

	public static boolean verificarUsuario(String usuario) throws NamingException, ServiciosException {

		UsuarioBeanRemote usuarioBean = (UsuarioBeanRemote) InitialContext
				.doLookup("PFT-Crianza_EJB/UsuarioBean!com.servicios.UsuarioBeanRemote");

		boolean existe;

		Usuario u = usuarioBean.obtenerUsuarioIgual(usuario);

		if (u != null) {
			existe = false;
		} else {
			existe = true;
		}

		return existe;

	}

	public static boolean verificarEnfermedad(Long idEnfermedad)
			throws NamingException, ServiciosException, EnfermedadException {

		EnfermedadBeanRemote enfermedadBean = (EnfermedadBeanRemote) InitialContext
				.doLookup("PFT-Crianza_EJB/EnfermedadBean!com.servicios.EnfermedadBeanRemote");

		boolean existe;

		Enfermedad enfermedad = enfermedadBean.obtenerEnfermedad(idEnfermedad);

		if (enfermedad != null) {
			existe = false;
		} else {
			existe = true;
		}

		return existe;
	}

	public static boolean verificarNroCaravanaTernero(Long nroCaravana)
			throws NamingException, ServiciosException, NroCaravanaExistente {

		TernerosBeanRemote ternerosBean = (TernerosBeanRemote) InitialContext
				.doLookup("PFT-Crianza_EJB/TernerosBean!com.servicios.TernerosBeanRemote");

		boolean existe;

		Ternero ternero = ternerosBean.obtenerporNroCaravana(nroCaravana);

		if (ternero == null) {
			existe = false;
		} else {
			existe = true;
		}

		return existe;

	}

	public static boolean verificarTerneroVivo(Long idTernero) throws NamingException, ServiciosException {

		TernerosBeanRemote ternerosBean = (TernerosBeanRemote) InitialContext
				.doLookup("PFT-Crianza_EJB/TernerosBean!com.servicios.TernerosBeanRemote");

		boolean existe;

		Ternero ternero = ternerosBean.obtenerTernero(idTernero);

		Date fechaMuerte = ternero.getFechaMuerte();
		Date fechaBaja = ternero.getFechaBaja();

		if (fechaMuerte == null && fechaBaja == null) {
			existe = false;
		} else {
			existe = true;
		}

		return existe;
	}

	private boolean verificarNombre(String nombre) {

		// FUNCIONANDO
		// Esto valida que el Alimento ingresado sea un valor del enum
		String t1 = TipoAlimento.LECHE.toString();
		String t2 = TipoAlimento.CALOSTRO_FORZADO.toString();
		String t3 = TipoAlimento.CALOSTRO_NATURAL.toString();
		String t4 = TipoAlimento.INICIADOR.toString();
		String t5 = TipoAlimento.RACION.toString();
		String t6 = TipoAlimento.SUSTITUTO_LACTEO.toString();

		boolean salida;

		String text = String.valueOf(nombre);

		if (text.equals(t1)) {
			salida = false;
		} else if (text.equals((t2))) {
			salida = false;
		} else if (text.equals(t3)) {
			salida = false;
		} else if (text.equals(t4)) {
			salida = false;
		} else if (text.equals(t5)) {
			salida = false;
		} else if (text.equals(t6)) {
			salida = false;
		} else {
			salida = true;
		}
		return salida;
	}

	public static boolean verificarDecimal(BigDecimal cantidad) {

		// FUNCIONANDO

		boolean salida;
				
		String text = String.valueOf(cantidad);
				
		int decimales = text.indexOf(".");
		
		System.out.println(text);
		
		int largoDeciales = decimales < 0 ? 0 : text.length() - decimales - 1;

		if (largoDeciales > 2) {
			salida = true;
		} else {
			salida = false;
		}
		return salida;
	}

	public static boolean verificarNumero(BigDecimal num) {

		// FUNCIONANDO
		// Tomo el largo de los enteros, si es > exception
		boolean salida = true;

		int largo = num.signum() == 0 ? 1 : num.precision() - num.scale();

		if (largo > 5) {
			salida = true;
		} else {
			salida = false;
		}
		return salida;

	}
	
	public static boolean verificarPeso(BigDecimal num) {

		// FUNCIONANDO
		// Tomo el largo de los enteros, si es > exception
		boolean salida = true;

		int largo = num.signum() == 0 ? 1 : num.precision() - num.scale();

		if (largo > 4) {
			salida = true;
		} else {
			salida = false;
		}
		return salida;

	}

	public static boolean verificarPositivo(BigDecimal num) {

		// FUNCIONANDO
		boolean salida;

		Double numeroIngreso = num.doubleValue();

		if (numeroIngreso > 0) {
			salida = true;
		} else {
			salida = false;
		}
		return salida;

	}

	public static boolean verificarMayorCero(BigDecimal num) {

		// FUNCIONANDO
		boolean salida;

		Double numeroIngreso = num.doubleValue();

		if (numeroIngreso >= 0) {
			salida = true;
		} else {
			salida = false;
		}
		return salida;

	}

	public static boolean verificarFecha(Date date) {

		// FUNCIONANDO
		boolean salida;

		Date hoy = new Date();

		Date fecha1 = date;

		Calendar esteA�o = Calendar.getInstance();
		esteA�o.setTime(hoy);
		int corrienteA�o = esteA�o.get(Calendar.YEAR);

		Calendar pasadoA�o = Calendar.getInstance();
		pasadoA�o.setTime(fecha1);
		pasadoA�o.add(Calendar.YEAR, 0);
		int pasado = pasadoA�o.get(Calendar.YEAR);

		if (fecha1.after(hoy)) {
			salida = true;
		} else if (pasado < corrienteA�o) {
			salida = true;
		} else {
			salida = false;
		}
		return salida;
	}

	public static boolean verificarFormatoFecha(Date date) {

		boolean salida;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
			sdf.format(date);
			salida = false;
		} catch (Exception e) {
			salida = true;
		}
		return salida;

	}

	public static boolean verificarObservaciones(String obs) {

		boolean salida;

		if (obs.length() > 250) {
			salida = true;
		} else {
			salida = false;
		}
		return salida;
	}

}
