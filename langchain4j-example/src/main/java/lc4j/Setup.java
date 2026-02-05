package lc4j;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

public class Setup {
	public static void main(String[] args) {
		System.out.println("Hello world!!");
		// System.out.println(System.getenv("OPENAI_API_KEY"));
		
		String apiKey = System.getenv("OPENAI_API_KEY");
		
		ChatModel cmodel = OpenAiChatModel.builder()
				.apiKey(apiKey)
				.modelName(OpenAiChatModelName.GPT_4_1_MINI)
				.build();
		
		// Method 1
		//String answer = cmodel.chat("Why should I learn Java?");
		
		UserMessage usrmsg = UserMessage.from("Why should i learn Java?");
		
		ChatResponse answer = cmodel.chat(usrmsg);
		
		// System.out.println(answer);
		System.out.println(answer.aiMessage().text());
		
		
	}
	
}


/*
Output
Learning Java can be highly beneficial for several reasons:

1. **Wide Usage and Demand**: Java is one of the most popular programming languages globally and is extensively used in enterprise environments, Android app development, web applications, and large systems. As a result, there’s a strong demand for Java developers in the job market.

2. **Platform Independence**: Java’s “write once, run anywhere” philosophy means that code written in Java can run on any device equipped with the Java Virtual Machine (JVM). This makes Java a versatile choice across different platforms.

3. **Robust and Secure**: Java has strong memory management, exception handling, and security features, making it suitable for building reliable and secure applications.

4. **Large Ecosystem and Community**: Java has a vast ecosystem of libraries, frameworks (like Spring, Hibernate), and tools that accelerate development. Additionally, the active community means plenty of resources, tutorials, and support.

5. **Good Foundation for Learning Other Languages**: Java is an object-oriented language with a clear syntax, which helps build a strong programming foundation, making it easier to learn other languages such as C++, C#, or Kotlin.

6. **Supports Large-scale Systems**: Many large enterprises use Java to build scalable, high-performance backend systems, making it a preferred choice for critical applications.

7. **Android Development**: Though Kotlin is increasingly popular, Java remains a primary language for Android app development and is supported by official tools.

8. **Employment Opportunities & Salary**: Java developers are consistently in demand, often commanding competitive salaries across various industries.

If you aim for a versatile, widely-used, and career-friendly programming language, learning Java is an excellent investment.

*/