package conversationhistory;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

import java.util.Scanner;

public class ChatWithContext {

    public static void main(String[] args) {
		String apiKey = System.getenv("OPENAI_API_KEY");

        Scanner userinput;
        String cmdline;

        ChatModel cmodel = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(OpenAiChatModelName.GPT_4)
                .build();
        
        // for Conversation history
        ChatMemory cm = MessageWindowChatMemory.withMaxMessages(100); // it is an instance of ChatMemory that is capable of saving 100 chat messages and it uses the implementation MessageWindowChatMemory

        SystemMessage sysmsg = new SystemMessage("""
                    You are a polite software consultant with deep expertise in teaching AI and Machine Learning.
                """);
        cm.add(sysmsg);


        while (true) {
            System.out.print("prompt> ");

            userinput = new Scanner(System.in);
            cmdline = userinput.nextLine();

            if (cmdline.isBlank())       // If nothing, do nothing
                continue;

            UserMessage usrmsg = UserMessage.from(cmdline);   // create the prompt
            cm.add(usrmsg);                                   // Add the user prompt to the ChatMemory

            var answer = cmodel.chat(cm.messages());  // send the context as messages and save the response
            var response = answer.aiMessage().text();

            System.out.println(response);

            cm.add(UserMessage.from(response));     // Add the response from the assistant
                                                    // Could have added answer.aiMessage()
            // instead of 'UserMessage.from(response)', i could have said answer.aiMessage()
        }
    }
}
