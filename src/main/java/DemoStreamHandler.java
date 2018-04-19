import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

public class DemoStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                     .addRequestHandlers(new HelloDemoRequestHandler(),
                                         new HelpIntentHandler(),
                                         new RememberNoteHandler(),
                                         new GetNotesHandler())
                     .build();
    }

    public DemoStreamHandler() {
        super(getSkill());
    }
}
