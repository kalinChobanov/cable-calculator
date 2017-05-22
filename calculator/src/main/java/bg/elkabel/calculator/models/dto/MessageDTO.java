/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.elkabel.calculator.models.dto;

import bg.elkabel.calculator.models.dto.enumeration.MessageType;



/**
 *
 * @author Kalin
 */
public class MessageDTO {

	private String fieldName;
	private String message;
	private MessageType type;

	public MessageDTO() {
		super();
	}

	public MessageDTO(MessageType type, String message, String fieldName) {
		super();
		this.message = message;
		this.type = type;
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

}
