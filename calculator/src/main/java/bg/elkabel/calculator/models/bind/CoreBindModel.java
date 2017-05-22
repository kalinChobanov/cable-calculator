/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.elkabel.calculator.models.bind;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Kalin
 */
public class CoreBindModel {
	
	@NotBlank(message = "error.core.name.blank")
	@Min(value = 3, message = "error.core.name.size")
	private String name;
	
	@NotNull(message = "error.core.size.notnull")
	private double coreSize;
	
	@NotBlank(message = "error.core.material.blank")
	private String material;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCoreSize() {
		return coreSize;
	}

	public void setCoreSize(double coreSize) {
		this.coreSize = coreSize;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	
	
}
