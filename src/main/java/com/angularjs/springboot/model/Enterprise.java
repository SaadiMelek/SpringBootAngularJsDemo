package com.angularjs.springboot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enterprise implements Serializable {

	private static final long serialVersionUID = -6851005666122534560L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer serial;
	private double capital;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Enterprise enterprise = (Enterprise) o;

		if (Double.compare(enterprise.capital, capital) != 0)
			return false;
		if (id != null ? !id.equals(enterprise.id) : enterprise.id != null)
			return false;
		if (name != null ? !name.equals(enterprise.name) : enterprise.name != null)
			return false;
		return serial != null ? serial.equals(enterprise.serial) : enterprise.serial == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (serial != null ? serial.hashCode() : 0);
		temp = Double.doubleToLongBits(capital);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", name=" + name + ", serial=" + serial + ", capital=" + capital + "]";
	}

}
