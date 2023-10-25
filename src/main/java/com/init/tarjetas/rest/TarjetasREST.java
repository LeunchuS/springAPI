package com.init.tarjetas.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.tarjetas.entity.*;

@RestController
@RequestMapping("tarjeta")
public class TarjetasREST {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	// @GetMapping
	public ResponseEntity<Object> operacion(@RequestBody Map<String, Object> json) {
		Map<String, Object> respuesta = new HashMap<>();

			Tarjeta t = null;

			try {
			String numero= String.valueOf(json.get("numero").toString());
			Long n = Long.parseLong(numero);
			
			switch (n.toString()) {
			case "1234123412341234":
				t = new Visa(1234123412341234L, "Eldar Gonzales", LocalDate.of(2025, 04, 30), "VISA");
				break;
			case "3456345634563456":
				t = new Nara(3456345634563456L, "Eldar Giménez", LocalDate.of(2023, 04, 30), "NARA");
				break;
			case "6789678967896789":
				t = new Amex(6789678967896789L, "Eldar Perez", LocalDate.of(2023, 12, 31), "AMEX");
				break;
			}

				respuesta.put("marca", t.getMarca());
				respuesta.put("tasa", t.tasa());
				if (json.containsKey("valor"))
					respuesta.put("valor", t.tasa(Float.parseFloat(json.get("valor").toString())));
		
			}catch(Exception e) {
				if(respuesta.isEmpty())
					respuesta.put("mensaje", "revise el numero de la tarjeta");
				else if(respuesta.containsKey("marca")) {
					respuesta.clear();
					respuesta.put("mensaje", "revise el valor");
				}
			}
		
		return ResponseEntity.ok(respuesta);
	}

}
