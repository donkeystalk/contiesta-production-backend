package contiesta.production.backend.marshallers;

import java.io.IOException;

import org.springframework.oxm.XmlMappingException;

public interface EveMarshaller<T> {
	T unmarshallXMLToObject(String xml);
}
