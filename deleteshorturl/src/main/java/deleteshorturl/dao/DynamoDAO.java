package deleteshorturl.dao;

import java.util.HashMap;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ReturnValue;

public class DynamoDAO implements DAO {

    private final static String TABLE_URL_ITEM = "URLItem";

	private final DynamoDbClient client;
	
	private final static String PK="shortURL";


    public DynamoDAO(DynamoDbClient client) {
		this.client=client;
	}

	@Override
	public boolean deleteById(String shortPath) {
		HashMap<String, AttributeValue> itemKey = new HashMap<>();
		itemKey.put(PK, AttributeValue.fromS(shortPath));
		DeleteItemRequest request = DeleteItemRequest.builder()
				.tableName(TABLE_URL_ITEM)
				.key(itemKey)
				.returnValues(ReturnValue.ALL_OLD)
				.build(); 
		var res = client.deleteItem(request);
		if (!res.attributes().isEmpty()){
			return false;
		}else {
			return true;
		}
	}
    
}
