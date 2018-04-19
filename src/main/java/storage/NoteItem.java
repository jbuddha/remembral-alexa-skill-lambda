package storage;

import static storage.NoteItem.TABLE_NAME;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = TABLE_NAME)
public class NoteItem {

    private String userId;
    private String time;
    private String text;

    public final static String TABLE_NAME = "demo_notes";
    public final static String USER_ID_ATTR = "user_id";
    public final static String TEXT_ATTR = "text";
    public final static String TIME_ATTR = "timestamp";

    @DynamoDBHashKey(attributeName = USER_ID_ATTR)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = TIME_ATTR)
    @DynamoDBRangeKey
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @DynamoDBAttribute(attributeName = TEXT_ATTR)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
