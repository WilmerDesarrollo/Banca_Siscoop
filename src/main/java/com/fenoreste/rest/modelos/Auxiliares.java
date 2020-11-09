package com.fenoreste.rest.modelos;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auxiliares")
public class Auxiliares implements Serializable{
	
	private static final long SerialVersionUID=1L;
	
	@Id
	@Column(name="idorigen")
	private Integer idorigen;
	@Column(name="idgrupo")
	private Integer idgrupo;
	@Column(name="idsocio")
	private Integer idsocio;
	@Column
	private Integer idorigenp;
	@Column
	private Integer idproducto;
	@Column 
	private Integer idauxiliar;
	@Column
	private Date fechaape;
	@Column
	private Integer elaboro;
	@Column 
	private Integer autorizo;
	@Column
	private Integer estatus;
	@Column
	private Double tasaio;
	@Column
	private Double tasaim;
	@Column 
	private Double tasaiod;
	@Column
	private Double montosolicitado;
	@Column 
	private Double montoautorizado;
	@Column 
	private Double montoprestado;
	@Column
	private Integer idfinalidad;
	@Column 
	private Integer plazo;
	@Column
	private Double periodoabonos;
	@Column
	private Double saldoinicial;
	@Column 
	private Double saldo;
	@Column
	private Double io;
	@Column
	private Double idnc;
	@Column 
	private Double ieco;
	@Column
	private Double im;
	@Column
	private Double iva;
	@Column
	private Date fechaactivacion;
	@Column 
	private Date fechaumi;
	@Column
	private String idnotas;
	@Column
	private Integer tipoprestamo;
	@Column
	private String cartera;
	@Column
	private Double contaidnc;
	@Column
	private Double contaieco;
	@Column 
	private Double reservaidnc;
	@Column
	private Double reservacapital;
	@Column
	private Integer tipoamortizacion;
	@Column
	private Double saldodiacum;
	@Column
	private Date fechacartera;
	@Column
	private Date fechauma;
	@Column
	private Double ivaidnccal;
	@Column
	private Double ivaidncpag;
	@Column
	private Integer tiporeferencia;
	@Column 
	private Integer calificacion;
	@Column
	private Integer pagodiafijo;
	@Column     
    private Double  iodif              ;
    @Column
    private Double  garantia           ;
    @Column
    private Double  saldodiacummi      ;
    @Column
    private Double  comision           ;
    @Column
    private Date    fechasdiacum       ;
    @Column
    private Double  prc_comision       ;
    @Column
    private Double  sobreprecio        ;
    @Column
    private Double  comision_np        ;
    @Column
    private Boolean pagos_dia_ultimo   ;
    @Column
    private Integer tipo_dv            ;
    @Column
    private Date    fecha_solicitud    ;
    @Column
    private Date    fecha_autorizacion ;
    @Column
    private Double  idncm              ;
    @Column
    private Double  iecom              ;
    @Column
    private Double  reservaidncm       ;
    @Column
    private Double  tasa_cat_gat       ;
    @Column
    private Double  retencion          ;
	
	public Auxiliares() {
	
	}

	public Auxiliares(Integer idorigen, Integer idgrupo, Integer idsocio, Integer idorigenp, Integer idproducto,
			Integer idauxiliar, Date fechaape, Integer elaboro, Integer autorizo, Integer estatus, Double tasaio,
			Double tasaim, Double tasaiod, Double montosolicitado, Double montoautorizado, Double montoprestado,
			Integer idfinalidad, Integer plazo, Double periodoabonos, Double saldoinicial, Double saldo, Double io,
			Double idnc, Double ieco, Double im, Double iva, Date fechaactivacion, Date fechaumi, String idnotas,
			Integer tipoprestamo, String cartera, Double contaidnc, Double contaieco, Double reservaidnc,
			Double reservacapital, Integer tipoamortizacion, Double saldodiacum, Date fechacartera, Date fechauma,
			Double ivaidnccal, Double ivaidncpag, Integer tiporeferencia, Integer calificacion, Integer pagodiafijo,
			Double iodif, Double garantia, Double saldodiacummi, Double comision, Date fechasdiacum,
			Double prc_comision, Double sobreprecio, Double comision_np, Boolean pagos_dia_ultimo, Integer tipo_dv,
			Date fecha_solicitud, Date fecha_autorizacion, Double idncm, Double iecom, Double reservaidncm,
			Double tasa_cat_gat, Double retencion) {
		this.idorigen = idorigen;
		this.idgrupo = idgrupo;
		this.idsocio = idsocio;
		this.idorigenp = idorigenp;
		this.idproducto = idproducto;
		this.idauxiliar = idauxiliar;
		this.fechaape = fechaape;
		this.elaboro = elaboro;
		this.autorizo = autorizo;
		this.estatus = estatus;
		this.tasaio = tasaio;
		this.tasaim = tasaim;
		this.tasaiod = tasaiod;
		this.montosolicitado = montosolicitado;
		this.montoautorizado = montoautorizado;
		this.montoprestado = montoprestado;
		this.idfinalidad = idfinalidad;
		this.plazo = plazo;
		this.periodoabonos = periodoabonos;
		this.saldoinicial = saldoinicial;
		this.saldo = saldo;
		this.io = io;
		this.idnc = idnc;
		this.ieco = ieco;
		this.im = im;
		this.iva = iva;
		this.fechaactivacion = fechaactivacion;
		this.fechaumi = fechaumi;
		this.idnotas = idnotas;
		this.tipoprestamo = tipoprestamo;
		this.cartera = cartera;
		this.contaidnc = contaidnc;
		this.contaieco = contaieco;
		this.reservaidnc = reservaidnc;
		this.reservacapital = reservacapital;
		this.tipoamortizacion = tipoamortizacion;
		this.saldodiacum = saldodiacum;
		this.fechacartera = fechacartera;
		this.fechauma = fechauma;
		this.ivaidnccal = ivaidnccal;
		this.ivaidncpag = ivaidncpag;
		this.tiporeferencia = tiporeferencia;
		this.calificacion = calificacion;
		this.pagodiafijo = pagodiafijo;
		this.iodif = iodif;
		this.garantia = garantia;
		this.saldodiacummi = saldodiacummi;
		this.comision = comision;
		this.fechasdiacum = fechasdiacum;
		this.prc_comision = prc_comision;
		this.sobreprecio = sobreprecio;
		this.comision_np = comision_np;
		this.pagos_dia_ultimo = pagos_dia_ultimo;
		this.tipo_dv = tipo_dv;
		this.fecha_solicitud = fecha_solicitud;
		this.fecha_autorizacion = fecha_autorizacion;
		this.idncm = idncm;
		this.iecom = iecom;
		this.reservaidncm = reservaidncm;
		this.tasa_cat_gat = tasa_cat_gat;
		this.retencion = retencion;
	}

	public Integer getIdorigen() {
		return idorigen;
	}

	public void setIdorigen(Integer idorigen) {
		this.idorigen = idorigen;
	}

	public Integer getIdgrupo() {
		return idgrupo;
	}

	public void setIdgrupo(Integer idgrupo) {
		this.idgrupo = idgrupo;
	}

	public Integer getIdsocio() {
		return idsocio;
	}

	public void setIdsocio(Integer idsocio) {
		this.idsocio = idsocio;
	}

	public Integer getIdorigenp() {
		return idorigenp;
	}

	public void setIdorigenp(Integer idorigenp) {
		this.idorigenp = idorigenp;
	}

	public Integer getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public Integer getIdauxiliar() {
		return idauxiliar;
	}

	public void setIdauxiliar(Integer idauxiliar) {
		this.idauxiliar = idauxiliar;
	}

	public Date getFechaape() {
		return fechaape;
	}

	public void setFechaape(Date fechaape) {
		this.fechaape = fechaape;
	}

	public Integer getElaboro() {
		return elaboro;
	}

	public void setElaboro(Integer elaboro) {
		this.elaboro = elaboro;
	}

	public Integer getAutorizo() {
		return autorizo;
	}

	public void setAutorizo(Integer autorizo) {
		this.autorizo = autorizo;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Double getTasaio() {
		return tasaio;
	}

	public void setTasaio(Double tasaio) {
		this.tasaio = tasaio;
	}

	public Double getTasaim() {
		return tasaim;
	}

	public void setTasaim(Double tasaim) {
		this.tasaim = tasaim;
	}

	public Double getTasaiod() {
		return tasaiod;
	}

	public void setTasaiod(Double tasaiod) {
		this.tasaiod = tasaiod;
	}

	public Double getMontosolicitado() {
		return montosolicitado;
	}

	public void setMontosolicitado(Double montosolicitado) {
		this.montosolicitado = montosolicitado;
	}

	public Double getMontoautorizado() {
		return montoautorizado;
	}

	public void setMontoautorizado(Double montoautorizado) {
		this.montoautorizado = montoautorizado;
	}

	public Double getMontoprestado() {
		return montoprestado;
	}

	public void setMontoprestado(Double montoprestado) {
		this.montoprestado = montoprestado;
	}

	public Integer getIdfinalidad() {
		return idfinalidad;
	}

	public void setIdfinalidad(Integer idfinalidad) {
		this.idfinalidad = idfinalidad;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	public Double getPeriodoabonos() {
		return periodoabonos;
	}

	public void setPeriodoabonos(Double periodoabonos) {
		this.periodoabonos = periodoabonos;
	}

	public Double getSaldoinicial() {
		return saldoinicial;
	}

	public void setSaldoinicial(Double saldoinicial) {
		this.saldoinicial = saldoinicial;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getIo() {
		return io;
	}

	public void setIo(Double io) {
		this.io = io;
	}

	public Double getIdnc() {
		return idnc;
	}

	public void setIdnc(Double idnc) {
		this.idnc = idnc;
	}

	public Double getIeco() {
		return ieco;
	}

	public void setIeco(Double ieco) {
		this.ieco = ieco;
	}

	public Double getIm() {
		return im;
	}

	public void setIm(Double im) {
		this.im = im;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Date getFechaactivacion() {
		return fechaactivacion;
	}

	public void setFechaactivacion(Date fechaactivacion) {
		this.fechaactivacion = fechaactivacion;
	}

	public Date getFechaumi() {
		return fechaumi;
	}

	public void setFechaumi(Date fechaumi) {
		this.fechaumi = fechaumi;
	}

	public String getIdnotas() {
		return idnotas;
	}

	public void setIdnotas(String idnotas) {
		this.idnotas = idnotas;
	}

	public Integer getTipoprestamo() {
		return tipoprestamo;
	}

	public void setTipoprestamo(Integer tipoprestamo) {
		this.tipoprestamo = tipoprestamo;
	}

	public String getCartera() {
		return cartera;
	}

	public void setCartera(String cartera) {
		this.cartera = cartera;
	}

	public Double getContaidnc() {
		return contaidnc;
	}

	public void setContaidnc(Double contaidnc) {
		this.contaidnc = contaidnc;
	}

	public Double getContaieco() {
		return contaieco;
	}

	public void setContaieco(Double contaieco) {
		this.contaieco = contaieco;
	}

	public Double getReservaidnc() {
		return reservaidnc;
	}

	public void setReservaidnc(Double reservaidnc) {
		this.reservaidnc = reservaidnc;
	}

	public Double getReservacapital() {
		return reservacapital;
	}

	public void setReservacapital(Double reservacapital) {
		this.reservacapital = reservacapital;
	}

	public Integer getTipoamortizacion() {
		return tipoamortizacion;
	}

	public void setTipoamortizacion(Integer tipoamortizacion) {
		this.tipoamortizacion = tipoamortizacion;
	}

	public Double getSaldodiacum() {
		return saldodiacum;
	}

	public void setSaldodiacum(Double saldodiacum) {
		this.saldodiacum = saldodiacum;
	}

	public Date getFechacartera() {
		return fechacartera;
	}

	public void setFechacartera(Date fechacartera) {
		this.fechacartera = fechacartera;
	}

	public Date getFechauma() {
		return fechauma;
	}

	public void setFechauma(Date fechauma) {
		this.fechauma = fechauma;
	}

	public Double getIvaidnccal() {
		return ivaidnccal;
	}

	public void setIvaidnccal(Double ivaidnccal) {
		this.ivaidnccal = ivaidnccal;
	}

	public Double getIvaidncpag() {
		return ivaidncpag;
	}

	public void setIvaidncpag(Double ivaidncpag) {
		this.ivaidncpag = ivaidncpag;
	}

	public Integer getTiporeferencia() {
		return tiporeferencia;
	}

	public void setTiporeferencia(Integer tiporeferencia) {
		this.tiporeferencia = tiporeferencia;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public Integer getPagodiafijo() {
		return pagodiafijo;
	}

	public void setPagodiafijo(Integer pagodiafijo) {
		this.pagodiafijo = pagodiafijo;
	}

	public Double getIodif() {
		return iodif;
	}

	public void setIodif(Double iodif) {
		this.iodif = iodif;
	}

	public Double getGarantia() {
		return garantia;
	}

	public void setGarantia(Double garantia) {
		this.garantia = garantia;
	}

	public Double getSaldodiacummi() {
		return saldodiacummi;
	}

	public void setSaldodiacummi(Double saldodiacummi) {
		this.saldodiacummi = saldodiacummi;
	}

	public Double getComision() {
		return comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

	public Date getFechasdiacum() {
		return fechasdiacum;
	}

	public void setFechasdiacum(Date fechasdiacum) {
		this.fechasdiacum = fechasdiacum;
	}

	public Double getPrc_comision() {
		return prc_comision;
	}

	public void setPrc_comision(Double prc_comision) {
		this.prc_comision = prc_comision;
	}

	public Double getSobreprecio() {
		return sobreprecio;
	}

	public void setSobreprecio(Double sobreprecio) {
		this.sobreprecio = sobreprecio;
	}

	public Double getComision_np() {
		return comision_np;
	}

	public void setComision_np(Double comision_np) {
		this.comision_np = comision_np;
	}

	public Boolean getPagos_dia_ultimo() {
		return pagos_dia_ultimo;
	}

	public void setPagos_dia_ultimo(Boolean pagos_dia_ultimo) {
		this.pagos_dia_ultimo = pagos_dia_ultimo;
	}

	public Integer getTipo_dv() {
		return tipo_dv;
	}

	public void setTipo_dv(Integer tipo_dv) {
		this.tipo_dv = tipo_dv;
	}

	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}

	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}

	public Date getFecha_autorizacion() {
		return fecha_autorizacion;
	}

	public void setFecha_autorizacion(Date fecha_autorizacion) {
		this.fecha_autorizacion = fecha_autorizacion;
	}

	public Double getIdncm() {
		return idncm;
	}

	public void setIdncm(Double idncm) {
		this.idncm = idncm;
	}

	public Double getIecom() {
		return iecom;
	}

	public void setIecom(Double iecom) {
		this.iecom = iecom;
	}

	public Double getReservaidncm() {
		return reservaidncm;
	}

	public void setReservaidncm(Double reservaidncm) {
		this.reservaidncm = reservaidncm;
	}

	public Double getTasa_cat_gat() {
		return tasa_cat_gat;
	}

	public void setTasa_cat_gat(Double tasa_cat_gat) {
		this.tasa_cat_gat = tasa_cat_gat;
	}

	public Double getRetencion() {
		return retencion;
	}

	public void setRetencion(Double retencion) {
		this.retencion = retencion;
	}

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}

	@Override
	public String toString() {
		return "Auxiliares [idorigen=" + idorigen + ", idgrupo=" + idgrupo + ", idsocio=" + idsocio + ", idorigenp="
				+ idorigenp + ", idproducto=" + idproducto + ", idauxiliar=" + idauxiliar + ", fechaape=" + fechaape
				+ ", elaboro=" + elaboro + ", autorizo=" + autorizo + ", estatus=" + estatus + ", tasaio=" + tasaio
				+ ", tasaim=" + tasaim + ", tasaiod=" + tasaiod + ", montosolicitado=" + montosolicitado
				+ ", montoautorizado=" + montoautorizado + ", montoprestado=" + montoprestado + ", idfinalidad="
				+ idfinalidad + ", plazo=" + plazo + ", periodoabonos=" + periodoabonos + ", saldoinicial="
				+ saldoinicial + ", saldo=" + saldo + ", io=" + io + ", idnc=" + idnc + ", ieco=" + ieco + ", im=" + im
				+ ", iva=" + iva + ", fechaactivacion=" + fechaactivacion + ", fechaumi=" + fechaumi + ", idnotas="
				+ idnotas + ", tipoprestamo=" + tipoprestamo + ", cartera=" + cartera + ", contaidnc=" + contaidnc
				+ ", contaieco=" + contaieco + ", reservaidnc=" + reservaidnc + ", reservacapital=" + reservacapital
				+ ", tipoamortizacion=" + tipoamortizacion + ", saldodiacum=" + saldodiacum + ", fechacartera="
				+ fechacartera + ", fechauma=" + fechauma + ", ivaidnccal=" + ivaidnccal + ", ivaidncpag=" + ivaidncpag
				+ ", tiporeferencia=" + tiporeferencia + ", calificacion=" + calificacion + ", pagodiafijo="
				+ pagodiafijo + ", iodif=" + iodif + ", garantia=" + garantia + ", saldodiacummi=" + saldodiacummi
				+ ", comision=" + comision + ", fechasdiacum=" + fechasdiacum + ", prc_comision=" + prc_comision
				+ ", sobreprecio=" + sobreprecio + ", comision_np=" + comision_np + ", pagos_dia_ultimo="
				+ pagos_dia_ultimo + ", tipo_dv=" + tipo_dv + ", fecha_solicitud=" + fecha_solicitud
				+ ", fecha_autorizacion=" + fecha_autorizacion + ", idncm=" + idncm + ", iecom=" + iecom
				+ ", reservaidncm=" + reservaidncm + ", tasa_cat_gat=" + tasa_cat_gat + ", retencion=" + retencion
				+ "]";
	}
	
	
	
	
	
	
	
}
