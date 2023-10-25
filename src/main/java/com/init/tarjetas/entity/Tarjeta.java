package com.init.tarjetas.entity;
import java.time.LocalDate;

public abstract class Tarjeta {
	private String marca;
	private long numero;
	private String cardholder;
	private LocalDate fechaVencimiento;
	
	public Tarjeta(long numero, String cardholder, LocalDate fechaVencimiento, String marca ) {
		this.numero = numero;
		this.cardholder = cardholder;
		this.fechaVencimiento = fechaVencimiento;
		this.marca = marca;
	}
	
	public long getNumero() {return numero;}
	public void setNumero(long numero) {this.numero = numero;}
	public String getNombre() {return cardholder;}
	public void setNombre(String cardholder) {this.cardholder = cardholder;}
	public LocalDate getFechaExpiracion() {return fechaVencimiento;}
	public void setFechaExpiracion(LocalDate fechaExpiracion) {this.fechaVencimiento = fechaExpiracion;}
	public String getMarca() {return marca;}
	public void setMarca(String marca) {this.marca = marca;}
		
	public String toString() {
		return "Tarjeta => numero=" + numero + ", nombre=" + cardholder + ", fechaExpiraci�n="
				+ fechaVencimiento + ", marca=" + marca;
	}
	
	//devuelve la tasa en %
	public abstract float tasa();
	//devuelve el valor con la tasa incluida
	public abstract float tasa(float valor);
	//devuelve la marca de la tarjeta y la tasa en % y $ (string)
	public String tasaInformacion(float valor) {
		return getMarca() + " - tasa: "+ tasa() + "%|$"+(valor-tasa(valor));
	}

	public boolean validarTarjeta() {
		boolean anioMenor = (LocalDate.now().getYear()<fechaVencimiento.getYear());
		boolean anioIgual = (LocalDate.now().getYear()==fechaVencimiento.getYear());
		boolean mesMenorIgual = (LocalDate.now().getMonthValue()<=fechaVencimiento.getMonthValue());
		return (anioMenor || ( anioIgual && mesMenorIgual ));
	}
	

	public boolean compararTarjeta(Tarjeta tarjeta) {
		return this.numero == tarjeta.getNumero();
	}
	
	public boolean validarOperacion(float valor, float limite) {
		return (limite>(tasa(valor)));
	}
}
