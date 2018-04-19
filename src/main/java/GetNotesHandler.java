import storage.NoteItem;
import storage.NotesDDBClient;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Session;
import com.amazon.ask.request.Predicates;

import java.util.List;
import java.util.Optional;

public class GetNotesHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("GetNotes"));
    }

    public Optional<Response> handle(HandlerInput input) {
        NotesDDBClient ddbClient = NotesDDBClient.getInstance();

        System.out.println("User: " + input.getRequestEnvelope().getSession().getUser().getUserId());
        Session session = input.getRequestEnvelope().getSession();

        List<NoteItem> noteItems = ddbClient.getAllNotesForUser(session);

        String speechText = "You have saved " + noteItems.size() + " notes so far.";

        int i = 1;
        for (NoteItem item: noteItems) {
            speechText = speechText + " note " + i++ + ": " + item.getText() + "."+ System.lineSeparator();
        }
        return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withSimpleCard("Notes", speechText)
                    .build();
    }
}
