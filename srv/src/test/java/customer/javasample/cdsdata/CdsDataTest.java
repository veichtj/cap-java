package customer.javasample.cdsdata;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CdsDataTest {

    @Test
    public void createCdsData(){
        CdsData person = Struct.create(CdsData.class);
        person.put("salutation", "Mr");
        person.put("name", "John Doe");
        String expectedJson = "{\"name\":\"John Doe\",\"salutation\":\"Mr\"}";
        assertThat(person.toJson()).isEqualTo(expectedJson);
        assertThat(person).containsEntry("name","John Doe");

    }

}
