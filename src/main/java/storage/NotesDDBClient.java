package storage;

import com.amazon.ask.model.Session;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class NotesDDBClient {

    private static AmazonDynamoDBClient dynamoDBClient;
    private static DynamoDBMapper       mapper;

    private static NotesDDBClient INSTANCE = null;

    public static NotesDDBClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NotesDDBClient();
            NotesDDBClient.dynamoDBClient = new AmazonDynamoDBClient();
            dynamoDBClient.setRegion(Region.getRegion(Regions.US_WEST_2));
            NotesDDBClient.mapper = new DynamoDBMapper(dynamoDBClient);
        }
        return INSTANCE;
    }

    private NotesDDBClient() {

    }

    public NoteItem createNote(final Session session, final String text) {
        NoteItem noteItem = new NoteItem();
        noteItem.setUserId(session.getUser().getUserId());
        noteItem.setText(text);
        noteItem.setTime(LocalDateTime.now(ZoneId.of("UTC")).toString());
        mapper.save(noteItem);
        return noteItem;
    }

    public List<NoteItem> getAllNotesForUser(final Session session) {
        DynamoDBQueryExpression<NoteItem> query = new DynamoDBQueryExpression<>();
        NoteItem noteItem = new NoteItem();
        noteItem.setUserId(session.getUser().getUserId());
        query.setHashKeyValues(noteItem);
        return mapper.query(NoteItem.class, query);
    }
}
