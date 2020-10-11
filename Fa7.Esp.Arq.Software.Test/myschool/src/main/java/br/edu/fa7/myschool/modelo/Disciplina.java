package br.edu.fa7.myschool.modelo;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author charles.marques
 *
 */
public enum Disciplina {
	MATEMATICA, PORTUGUES, INGLES, ESPANHOL, FISICA, QUIMICA, HISTORIA, GEOGRAFIA, BIOLOGIA, ARTES;
	
	@Override
	public String toString() {
		switch (this) {
		case ARTES:
			return "Artes";
		case BIOLOGIA:
			return "Biologia";
		case ESPANHOL:
			return "Espanhol";
		case FISICA:
			return "Física";
		case GEOGRAFIA:
			return "Geografia";
		case HISTORIA:
			return "História";
		case INGLES:
			return "Ingês";
		case MATEMATICA:
			return "Matemática";
		case PORTUGUES:
			return "Português";
		case QUIMICA:
			return "Química";
		default:
			return null;
		}
	}

	public String toXML() {
		return new XStream().toXML(this);
	}
}
