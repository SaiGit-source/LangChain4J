package simplechatbot;

import java.util.Scanner;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

public class Chat {

	public static void main(String[] args) {
		       Scanner userinput;      // user inputted line as a Scanner
		       String cmdline;


		       ChatModel cmodel = OpenAiChatModel.builder()
		               .apiKey(System.getenv("OPENAI_API_KEY"))
		               .modelName(OpenAiChatModelName.GPT_4_O)
		               .build();


		       while (true) {
		           System.out.print("prompt> ");


		           userinput = new Scanner(System.in);
		           cmdline = userinput.nextLine();


		           if (cmdline.isBlank())       // If nothing, do nothing
		               continue;


		           UserMessage usrmsg = UserMessage.from(cmdline);
		           ChatResponse answer = cmodel.chat(usrmsg);
		           System.out.println(answer.aiMessage().text());
		       }
		   }
}

/*
prompt> Hi how's your day going?
Hello! I'm just a computer program, so I don't have feelings, but I'm here and ready to help you. How's your day going?
prompt> can you tell me how the weather is going to be in the next 10 days, in Durham, NC?
I'm unable to provide real-time or future weather forecasts. To get the most accurate and up-to-date weather information for Durham, NC, I recommend checking a reliable weather website or using a weather app like The Weather Channel, AccuWeather, or your local news station's website.
prompt> what are the advantages of Langchain4J?
Langchain4J is an implementation of the Langchain framework in Java, designed for building applications that use large language models (LLMs). While specific advantages can vary based on updates and individual project needs, some general advantages of using Langchain4J might include:

1. **Java Integration**: For organizations heavily invested in the Java ecosystem, Langchain4J provides a seamless way to integrate language models into their existing Java applications.

2. **Modular Architecture**: Like its counterparts in other languages, Langchain4J is designed to be modular, allowing developers to easily plug in different components and customize their workflows.

3. **Scalability**: Java is known for its performance and scalability. Using Langchain4J can help in building and scaling applications that demand high throughput and low latency.

4. **Interoperability**: Java applications often need to interact with various other systems and technologies. Langchain4J can be integrated with other Java libraries, frameworks, and tools, offering greater interoperability.

5. **Robustness**: Java's strong type system and error handling can lead to more robust applications. Langchain4J can leverage these features to reduce runtime errors and improve application stability.

6. **Community and Support**: The Java developer community is large and active, which means a wealth of resources, support, and libraries are available. Langchain4J benefits from this ecosystem.

7. **Maintainability**: Java's readability and widespread use contribute to easier maintenance and long-term support of applications built with Langchain4J.

8. **Security**: Java has a strong focus on security. Langchain4J applications can benefit from Java's security features to protect sensitive data processed by LLMs.

These advantages help make Langchain4J a compelling choice for developers looking to leverage LLMs within Java environments. However, as with any technology choice, it is important to assess how well it aligns with specific project requirements and constraints.
prompt> 

*/