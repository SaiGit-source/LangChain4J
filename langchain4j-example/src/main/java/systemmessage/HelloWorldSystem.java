package systemmessage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;

public class HelloWorldSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String apiKey = System.getenv("OPENAI_API_KEY");
		
		ChatModel cmodel = OpenAiChatModel.builder()
				.apiKey(apiKey)
				.modelName(OpenAiChatModelName.GPT_4_O)
				.temperature(.3) // temperature controls the randomness of how LLM generates the response. Higher the number, more randomness introduced into response. range of temperature varies between different providers, OpenAI ranges from 0.0 to 2.0. higher the temperature, more creative the response.  
				.timeout(Duration.ofSeconds(120)) // network timeout is 2 min
				.maxTokens(512) // a token is approx 3/4 of a word. set maxTokens to a smaller number so you avoid incurring costs. 
				.build(); // build() method returns an instance of the chat model
		
		System.out.println("Hello World System ---------------");
		List<ChatMessage> messages = new ArrayList<>();
		
		SystemMessage sysmsg = new SystemMessage("""
				You are a polite Java expert explaining concepts to a grammar school child.
				"""); // System message is the instruction on how to behave
				
		messages.add(sysmsg);
		
		UserMessage usrmsg = UserMessage.from("Why should I learn Java lambdas?");
		messages.add(usrmsg);
		
		ChatResponse answer = cmodel.chat(messages);
		
		System.out.println(answer.aiMessage().text());

	}

}


/*
Output

Hello World System ---------------
Imagine you have a box of LEGO bricks, and you want to build something cool with them. Sometimes, you might want to use a special piece that helps you build faster or in a more fun way. Java lambdas are like those special LEGO pieces for your code!

When you write programs in Java, you often tell the computer to do things step by step. But sometimes, you want to tell it to do something in a quicker and neater way. That's where lambdas come in! They help you write less code and make it easier to understand.

For example, if you have a list of toys and you want to find all the red ones, using lambdas can help you do that with just a few lines of code instead of many. It's like having a magic wand that helps you sort and find things faster!

Learning Java lambdas is like adding a new tool to your coding toolbox. It makes your programs cleaner and helps you solve problems in a smart way. Plus, it's fun to learn new things and see how they make your coding adventures even better!

*/