package com.vivs.cursomc.main;

import java.time.ZoneId;

public class DataJava8 {
	
	public static void main(String[] args) {
		//armazena apenas data sem fuso horario
		System.out.println(java.time.LocalDate.now());
		//armazena apenas Hora sem fuso horario
		System.out.println(java.time.LocalTime.now());
		//armazea data e hora sem fuso horario
		System.out.println(java.time.LocalDateTime.now());
		//Instant - UTC/GMT - armazena data + hora com fuso horario UTC/GMT - 0
		System.out.println(java.time.Instant.now());
		System.out.println(java.time.ZonedDateTime.now());
		System.out.println(java.time.LocalTime.of(11, 30));
		System.out.println(java.time.ZonedDateTime.of(java.time.LocalDateTime.now(), ZoneId.of("America/Sao_Paulo")));
	}
}
