package org.tottus.ventaonline.utils.validation;

public class ValidationContext {

	private String fieldName;
	private Object fieldValue;
	private int fieldSize;
	private String regex;
	private String message;
	private ValidationType validationType;
	
	public String getFieldName() {
		return fieldName;
	}
	public ValidationContext fieldName(String fieldName) {
		this.fieldName = fieldName;
		return this;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public ValidationContext fieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
		return this;
	}
	public ValidationType getValidationType() {
		return validationType;
	}
	public ValidationContext validationType(ValidationType validationType) {
		this.validationType = validationType;
		return this;
	}
	public int getFieldSize() {
		return fieldSize;
	}
	public ValidationContext fieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
		return this;
	}
	public String getRegex() {
		return regex;
	}
	public ValidationContext regex(String regex) {
		this.regex = regex;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ValidationContext message(String message) {
		this.message = message;
		return this;
	}
	
}
