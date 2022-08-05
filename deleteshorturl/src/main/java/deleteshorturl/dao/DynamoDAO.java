package deleteshorturl.dao;

import java.util.HashMap;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ReturnValue;

public class DynamoDAO implements DAO {

    private static final  String TABLE_URL_ITEM = "URLItem";

	private final DynamoDbClient client;
	
	private static final String PK="shortURL";


    public DynamoDAO(DynamoDbClient client) {
		this.client=client;
	}

	@Override
	public boolean deleteById(String shortPath) {
		System.out.println("dynamodao shortpath "+shortPath);

		HashMap<String, AttributeValue> itemKey = new HashMap<>();
		itemKey.put(PK, AttributeValue.fromS(shortPath));
		DeleteItemRequest request = DeleteItemRequest.builder()
				.tableName(TABLE_URL_ITEM)
				.key(itemKey)
				.returnValues(ReturnValue.ALL_OLD)
				.build(); 
		var res = client.deleteItem(request);
		res.sdkHttpResponse().statusCode();
		return res.sdkHttpResponse().statusCode()==200;
	}
    
}
