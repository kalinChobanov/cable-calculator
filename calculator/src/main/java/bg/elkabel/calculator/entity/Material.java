/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.elkabel.calculator.entity;


public enum Material {
	
	AL ("aluminium", 400),
	CU("copper", 140);
	
	private final String value;
	private final int capacity;

	private Material(String value, int capacity) {
		this.value = value;
		this.capacity = capacity;
	}
	
	public String getValue() {
		return value;
	}

	public int getCapacity() {
		return capacity;
	}

}
