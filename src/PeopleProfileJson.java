/**
* Made by Ruslan Rad, 2015, EIT SDE, Thanks to Pavel K.
**/

import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import model.HealthProfile;
import model.Person;
import sample.PeopleStore;

public class PeopleProfileJson {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		Person first = new Person ("Rad", "Ruslan", new HealthProfile(75, 1.89), "1992-06-07T15:05:00.000+03:00", (long)0001); 
		Person second = new Person ("Ded", "Moroz", new HealthProfile(90, 2.00), "1999-09-09T19:09:00.000+09:00", (long)0002); 
		Person third = new Person ("Putin", "Vova", new HealthProfile(75, 1.65), "1953-04-12T21:32:00.000+01:00", (long)0005); 
		
		people.getData().add(first);
		people.getData().add(second);
		people.getData().add(third);
	
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
    	ObjectMapper mapper = new ObjectMapper();
		
		JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        
        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), people);
        
    }
}
