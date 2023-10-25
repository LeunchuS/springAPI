package com.init.tarjetas.entity;
import java.time.LocalDate;

public class Amex extends Tarjeta{
	public Amex(long numero, String cardholder, LocalDate fechaExpiracion, String marca) {
		super(numero, cardholder, fechaExpiracion, "AMEX");
	}

	public float tasa() {
		//tasa = mes * 0.1 (Ejemplo 09*0.1)
		LocalDate fechaActual = LocalDate.now();
		float mes = fechaActual.getMonthValue();
		//Calculo la tasa en base a la funcion indicada
		float tasa = mes * 0.1f;
		//verifico que la tasa se encuentre en el rango 0.3 - 5
		tasa = Math.max(0.3f, tasa);
		tasa = Math.min(tasa, 5f);
		//System.out.println(tasa);
		return tasa;
	}
	
	public float tasa(float valor){
		valor = valor *(1+(tasa()/100));
		return valor;
	}
	


}