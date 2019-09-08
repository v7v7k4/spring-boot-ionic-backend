package com.vivs.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class URL {
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	//pega a string separa por virgula e adiciona em uma lista de inteiros
	public static List<Integer> decodeIntList(String s) {
		/*String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<vet.length;i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		
		return list;*/
		
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
		return Stream.of(s.split(","))
		      .map (elem -> new Integer(elem))
		      .collect(Collectors.toList());
		
	}

}
