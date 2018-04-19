import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class HelloDemoRequestHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("HelloDemo"));
    }

    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Let us begin the journey of skill development";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Demo", speechText)
                .build();
    }
}
