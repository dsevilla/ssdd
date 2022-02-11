package es.um.sisdist.RestTest.first;

import java.io.StringReader;
import java.io.StringWriter;


import es.um.sisdist.RestTest.first.Resources.Item;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class SerializeTest {

	public static void main(String[] args) {
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(Item.class);
			Marshaller m = context.createMarshaller();
			Item item = new Item();
			item.setUri("http://TestURI");

			m.marshal(item, writer);
			
			System.out.println(writer.toString());
			
			Unmarshaller um = context.createUnmarshaller();
			Item q = (Item)um.unmarshal(new StringReader(writer.toString()));
			System.out.println(q.getUri());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
