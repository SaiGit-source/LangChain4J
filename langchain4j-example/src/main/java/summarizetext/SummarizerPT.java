package summarizetext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

// Summarize text files using Prompt templates
public class SummarizerPT {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        List<ChatMessage> messages;
        SystemMessage sysmsg = SystemMessage.from("""
                You are an expert administrator with expertise in summarizing complex texts
                """);
        
		String apiKey = System.getenv("OPENAI_API_KEY");

        ChatModel model = OpenAiChatModel.builder()
				.apiKey(apiKey)
				.modelName(OpenAiChatModelName.GPT_4_O)
                .build();

        while (true) {
            messages = new ArrayList<>();
            messages.add(sysmsg);

            String fname = getUserInput("File> ");         // minimal error checking
            String level = getUserInput("Level> ");
            String language = getUserInput("Language> "); // Input

            if (fname.isEmpty() || level.isEmpty() || language.isEmpty())
                continue;

            UserMessage usrmsg = UserMessage.from(genPrompt(fname, level, language));
            messages.add(usrmsg);

            ChatResponse answer = model.chat(messages);
            System.out.println(answer.aiMessage().text());
        }
        }

    /**
     * genPrompt(String fileName, String summary_level, String language)
     *
     * @param fileName      - name of the file you want to send to the LLM
     * @param summary_level - requested response level
     * @param language      - text language, ie, english, italian, french, hindi, etc
     * @return String version of the prompt
     */
    static public String genPrompt(String fileName, String summary_level, String language) throws IOException {
        String myTemplate = """
                Please create a summary from the following text at a {{level}} level
                using a clear, succinct paragraph
                that captures the essence of the text, highlighting key themes and insights.
                Respond in {{language}}.  {{file}}
                """;
        PromptTemplate promptTemplate = PromptTemplate.from(myTemplate);

        Map<String, Object> variables = new HashMap<>();

        variables.put("level", summary_level);
        String pathname = System.getProperty("user.dir") + "/src/main/resources/" + fileName;
        variables.put("file", new String(Files.readAllBytes(Path.of(pathname))));
        variables.put("language", language);

        Prompt prompt = promptTemplate.apply(variables);
        return prompt.text();
    }

    /**
     * getUserInput(String pstring)
     *
     * @param pstring - string that reminds the user what to enter
     * @return What the user typed in
     * minimal error checking...
     */
    static public String getUserInput(String pstring) {
        String cmdline;

        System.out.print(pstring);    // prompt the user
        cmdline = new Scanner(System.in).nextLine();
        return cmdline.isBlank() ? "" : cmdline;
    }

}


/* Output

File> tomatoes.txt
Level> grammar school teacher
Language> Italian
Il testo fornisce una guida completa sulla coltivazione dei pomodori, un frutto molto popolare nei giardini domestici per la loro versatilità 
e gusto. La scelta tra iniziare dai semi o dalle piantine è cruciale, con le piantine che offrono un approccio meno impegnativo per 
i principianti. Coltivare dai semi richiede più tempo e risorse, ma permette una maggiore varietà. La guida sottolinea l'importanza del 
momento della piantagione, suggerendo di attendere il passaggio del pericolo di gelo e temperature notturne sopra i 10°C. Vengono forniti 
consigli sui criteri per selezionare piantine sane, come preparare il terreno, e l'importanza di un buon sistema di supporto per le piante. 
Cure di base come l'irrigazione, la fertilizzazione e la potatura sono fondamentali per la salute delle piante. Infine, il testo consiglia di 
monitorare regolarmente le piante per prevenire malattie e parassiti, e offre suggerimenti per la raccolta, assicurandosi che i pomodori non 
vadano sprecati.
File> 

*/

/* It summarizes file for 5th grade teacher
File> tomatoes.txt
Level> grammar school teacher for 5th grade
Language> English
Tomatoes are a favorite in home gardens because they're tasty, can save money, and come in many types. 
To grow them well, you can start with seedlings (young plants) or seeds. Seedlings are easier for beginners since the initial care is done by others, 
though they might have limited variety. Seeds offer more variety and the chance to learn, though they need more time and effort. 
When planting tomatoes, make sure it's warm enough and frost-free. Place them in sunny spots but ensure they have space to grow, 
and plant them deeply for strong roots. Watering should be deep and regular, avoiding wet leaves to prevent disease. 
Fertilize them based on signs from the plants, and prune depending on the tomato type to keep them healthy and productive. 
Lastly, check for pests and diseases often, and harvest when they're ripe. 
This planning and care can help even new gardeners succeed with tomatoes.
File> 
*/