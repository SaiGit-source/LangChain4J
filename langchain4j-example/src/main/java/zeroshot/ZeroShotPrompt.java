package zeroshot;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

public class ZeroShotPrompt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String apiKey = System.getenv("OPENAI_API_KEY");
		
		ChatModel cmodel = OpenAiChatModel.builder()
				.apiKey(apiKey)
				.modelName(OpenAiChatModelName.GPT_4_O)
				.temperature(.2) // temperature controls the randomness of how LLM generates the response. Higher the number, more randomness introduced into response. range of temperature varies between different providers, OpenAI ranges from 0.0 to 2.0. higher the temperature, more creative the response.  
				.timeout(Duration.ofSeconds(120)) // network timeout is 2 min
				.maxTokens(512) // a token is approx 3/4 of a word. set maxTokens to a smaller number so you avoid incurring costs. 
				.build(); // build() method returns an instance of the chat model
		
		List<ChatMessage> messages = new ArrayList<>();
		
		messages.add(UserMessage.from("Translate the following text into Italian"));
		
		String mytext = """
				Java is a powerful, object-oriented programming language widely used for building enterprise applications, web services, and Android apps. It is known for its portability because Java programs run on the Java Virtual Machine (JVM), allowing the same code to work across different operating systems. Java also provides strong memory management through automatic garbage collection, making it more reliable and secure for large-scale systems. With a rich ecosystem of libraries, frameworks like Spring, and strong community support, Java remains one of the most popular and in-demand programming languages in the software industry.
				""";
		
		messages.add(UserMessage.from(mytext));
		
		ChatResponse answer = cmodel.chat(messages);
		
		System.out.println("ENGLISH");
		System.out.println(mytext);
		System.out.println("ITALIAN");
		System.out.println(answer.aiMessage().text());
		
	}

}

/* Output

ENGLISH
Java is a powerful, object-oriented programming language widely used for building enterprise applications, web services, and Android apps. It is known for its portability because Java programs run on the Java Virtual Machine (JVM), allowing the same code to work across different operating systems. Java also provides strong memory management through automatic garbage collection, making it more reliable and secure for large-scale systems. With a rich ecosystem of libraries, frameworks like Spring, and strong community support, Java remains one of the most popular and in-demand programming languages in the software industry.

ITALIAN
Java è un potente linguaggio di programmazione orientato agli oggetti, ampiamente utilizzato per sviluppare applicazioni aziendali, servizi web e app Android. È noto per la sua portabilità, poiché i programmi Java vengono eseguiti sulla Java Virtual Machine (JVM), consentendo allo stesso codice di funzionare su diversi sistemi operativi. Java offre anche una gestione della memoria efficace grazie alla raccolta automatica dei rifiuti, rendendolo più affidabile e sicuro per sistemi su larga scala. Con un ricco ecosistema di librerie, framework come Spring e un forte supporto della comunità, Java rimane uno dei linguaggi di programmazione più popolari e richiesti nell'industria del software.


*/