package es.um.sisdist.RestTest.first.Resources;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemDetail {
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
