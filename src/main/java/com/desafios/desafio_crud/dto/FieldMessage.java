package com.desafios.desafio_crud.dto;

import java.security.KeyStore.PrivateKeyEntry;

public class FieldMessage 
{
	private String fieldName;
	private String messageString;
	
	public FieldMessage(){}
	public FieldMessage(String fieldName, String message)
	{this.fieldName = fieldName; this.messageString = message;}
	
	public String getFieldName(){return fieldName;}
	public String getMessage(){return messageString;}
}
