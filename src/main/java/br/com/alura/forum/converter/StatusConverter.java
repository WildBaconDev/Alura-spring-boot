package br.com.alura.forum.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.alura.forum.modelo.StatusTopico;

/* 
 * Fazendo um converter por fazer. Nesse caso ele estava resolvendo um erro do banco onde foi salvo os enums lower case porem na plicação estão upper case então estava dando erro.
 */
@Converter
public class StatusConverter implements AttributeConverter<StatusTopico, String>{

	@Override
	public String convertToDatabaseColumn(StatusTopico attribute) {
		return attribute.name();
	}

	@Override
	public StatusTopico convertToEntityAttribute(String dbData) {
		System.out.println("############################" + dbData);
		return StatusTopico.valueOf(dbData.toUpperCase());
	}

}
