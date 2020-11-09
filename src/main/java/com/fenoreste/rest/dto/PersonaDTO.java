package com.fenoreste.rest.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fenoreste.rest.dao.FacadeCustomer;
import com.fenoreste.rest.modelos.Persona;


public class PersonaDTO extends FacadeCustomer<Persona>{
      
    public PersonaDTO(Class<Persona> entityClass) {
	        super(entityClass);
    }
	 
		
	 private int idorigen;
	 @NotNull
	private int idgrupo;
	 @NotNull
	private int idsocio;
	 @NotNull
	private String calle;
	 @NotNull 
	private String numeroext;
	 @NotNull
	private String numeroint;
	 @NotNull 
	private int idcolonia;
	 @NotNull 
	private String entrecalles;
	@NotNull
	private Date fechanacimiento;
	 @NotNull 
	private String lugarnacimiento;
	 @NotNull 
	private int efnacimiento;
	 @NotNull
	private int sexo;
	 @NotNull
	private String telefono;
	 @NotNull
	private String telefonorecados;
	 @NotNull
	private boolean listanegra;
	 @NotNull
	private int estadocivil;
	 @NotNull
	private String idcoop;
	 @NotNull
	private int idsector;
	 @NotNull 
	private boolean estatus;
	 @NotNull
	private boolean aceptado;
	 @NotNull
	private Date fechaingreso;
	 @NotNull
	private Date fecharetiro;
	 @NotNull
	private Date fechaciudad;
	 @NotNull
	private int regimen_mat;
	 @NotNull
	private String nombre;
	 @NotNull
	private int medio_inf;
	 @NotNull
	private int requisitos;
	 @NotNull 
	private String appaterno;
     @NotNull
    private String apmaterno;
     @NotNull
    private int nacionalidad;
     @NotNull
    private int grado_estudios;
     @NotNull
    private int categoria;
     @NotNull
    private String rfc;
     @NotNull 
    private String curp;
	 @NotNull
	private String email;
	 @NotNull 
	private String razon_social;
	 @NotNull
	private int causa_baja;
	 @NotNull
	private int nivel_riesgo;
	 @NotNull
	private String celular;
	 @NotNull 
	private boolean rfc_valido;
	 @NotNull
	private int pais_nacimiento;
	 @NotNull 
	private int tipo_idoficial;
	 @NotNull 
	private String clave_idoficial;
	 @NotNull 
	private String fiel;
	 @NotNull
	private Timestamp fecha_modificacion;
	 @NotNull
	private int modifico;
	public int getIdorigen() {
		return idorigen;
	}
	public void setIdorigen(int idorigen) {
		this.idorigen = idorigen;
	}
	public int getIdgrupo() {
		return idgrupo;
	}
	public void setIdgrupo(int idgrupo) {
		this.idgrupo = idgrupo;
	}
	public int getIdsocio() {
		return idsocio;
	}
	public void setIdsocio(int idsocio) {
		this.idsocio = idsocio;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumeroext() {
		return numeroext;
	}
	public void setNumeroext(String numeroext) {
		this.numeroext = numeroext;
	}
	public String getNumeroint() {
		return numeroint;
	}
	public void setNumeroint(String numeroint) {
		this.numeroint = numeroint;
	}
	public int getIdcolonia() {
		return idcolonia;
	}
	public void setIdcolonia(int idcolonia) {
		this.idcolonia = idcolonia;
	}
	public String getEntrecalles() {
		return entrecalles;
	}
	public void setEntrecalles(String entrecalles) {
		this.entrecalles = entrecalles;
	}
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		if(fechanacimiento == null) {
			LocalDate fecha1=LocalDate.now();
			this.fechanacimiento=Date.valueOf(fecha1);
		}else {
			this.fechanacimiento = fechanacimiento;
		}
	}
	public String getLugarnacimiento() {
		return lugarnacimiento;
	}
	public void setLugarnacimiento(String lugarnacimiento) {
		this.lugarnacimiento = lugarnacimiento;
	}
	public int getEfnacimiento() {
		return efnacimiento;
	}
	public void setEfnacimiento(int efnacimiento) {
		this.efnacimiento = efnacimiento;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelefonorecados() {
		return telefonorecados;
	}
	public void setTelefonorecados(String telefonorecados) {
		this.telefonorecados = telefonorecados;
	}
	public boolean isListanegra() {
		return listanegra;
	}
	public void setListanegra(boolean listanegra) {
		this.listanegra = listanegra;
	}
	public int getEstadocivil() {
		return estadocivil;
	}
	public void setEstadocivil(int estadocivil) {
		this.estadocivil = estadocivil;
	}
	public String getIdcoop() {
		return idcoop;
	}
	public void setIdcoop(String idcoop) {
		this.idcoop = idcoop;
	}
	public int getIdsector() {
		return idsector;
	}
	public void setIdsector(int idsector) {
		this.idsector = idsector;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public boolean isAceptado() {
		return aceptado;
	}
	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}
	public Date getFechaingreso() {
		return fechaingreso;
	}
	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	public Date getFecharetiro() {
		return fecharetiro;
	}
	public void setFecharetiro(Date fecharetiro) {
		this.fecharetiro = fecharetiro;
	}
	public Date getFechaciudad() {
		return fechaciudad;
	}
	public void setFechaciudad(Date fechaciudad) {
		this.fechaciudad = fechaciudad;
	}
	public int getRegimen_mat() {
		return regimen_mat;
	}
	public void setRegimen_mat(int regimen_mat) {
		this.regimen_mat = regimen_mat;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getMedio_inf() {
		return medio_inf;
	}
	public void setMedio_inf(int medio_inf) {
		this.medio_inf = medio_inf;
	}
	public int getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(int requisitos) {
		this.requisitos = requisitos;
	}
	public String getAppaterno() {
		return appaterno;
	}
	public void setAppaterno(String appaterno) {
		this.appaterno = appaterno;
	}
	public String getApmaterno() {
		return apmaterno;
	}
	public void setApmaterno(String apmaterno) {
		this.apmaterno = apmaterno;
	}
	public int getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(int nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public int getGrado_estudios() {
		return grado_estudios;
	}
	public void setGrado_estudios(int grado_estudios) {
		this.grado_estudios = grado_estudios;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public int getCausa_baja() {
		return causa_baja;
	}
	public void setCausa_baja(int causa_baja) {
		this.causa_baja = causa_baja;
	}
	public int getNivel_riesgo() {
		return nivel_riesgo;
	}
	public void setNivel_riesgo(int nivel_riesgo) {
		this.nivel_riesgo = nivel_riesgo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public boolean isRfc_valido() {
		return rfc_valido;
	}
	public void setRfc_valido(boolean rfc_valido) {
		this.rfc_valido = rfc_valido;
	}
	public int getPais_nacimiento() {
		return pais_nacimiento;
	}
	public void setPais_nacimiento(int pais_nacimiento) {
		this.pais_nacimiento = pais_nacimiento;
	}
	public int getTipo_idoficial() {
		return tipo_idoficial;
	}
	public void setTipo_idoficial(int tipo_idoficial) {
		this.tipo_idoficial = tipo_idoficial;
	}
	public String getClave_idoficial() {
		return clave_idoficial;
	}
	public void setClave_idoficial(String clave_idoficial) {
		this.clave_idoficial = clave_idoficial;
	}
	public String getFiel() {
		return fiel;
	}
	public void setFiel(String fiel) {
		this.fiel = fiel;
	}
	public Timestamp getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(Timestamp fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public int getModifico() {
		return modifico;
	}
	public void setModifico(int modifico) {
		this.modifico = modifico;
	}
	
	 	 
		 
}
