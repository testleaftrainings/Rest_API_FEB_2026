package week6.day2;

import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

import week6.day2.pojos.RetrieveAllRecordsSampleResponse;

public class GenerateJsonSchemaUsingPojo {

	public static <T> String create(Class<T> classType) {
		SchemaGeneratorConfigBuilder schemaGeneratorConfigBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_7, OptionPreset.PLAIN_JSON);
	    SchemaGenerator schemaGenerator = new SchemaGenerator(schemaGeneratorConfigBuilder.build());
	    String jsonSchema = schemaGenerator.generateSchema(RetrieveAllRecordsSampleResponse.class).toPrettyString();	    
		return jsonSchema;
	}

}
