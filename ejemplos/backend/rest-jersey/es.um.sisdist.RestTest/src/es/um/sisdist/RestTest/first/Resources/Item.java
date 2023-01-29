package es.um.sisdist.RestTest.first.Resources;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {

	private String uri;
	private ItemDetail item_detail;
	
	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public void setItem_detail(ItemDetail item_detail) {
		this.item_detail = item_detail;
	}

	public ItemDetail getItem_detail() {
		return item_detail;
	}
	
}
