import storage.NotesDDBClient;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Session;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class RememberNoteHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("RememberNote"));
    }

    public Optional<Response> handle(HandlerInput input) {


        Session session = input.getRequestEnvelope().getSession();
        IntentRequest request = (IntentRequest) input.getRequestEnvelope().getRequest();
        Intent intent = request.getIntent();
        String note = intent.getSlots()
                            .get("note").getValue();

        NotesDDBClient ddbClient = NotesDDBClient.getInstance();

        ddbClient.createNote(session, note);

        String speechText = "Noted. " + note;

        return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withSimpleCard("Noted", speechText)
                    .build();
    }
}
