package prompttemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

public class SimpleTemplate {

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

		
		String myTemplate = "Please explain {{topic}} to a {{student_type}} using a clear, succinct paragraph";
		PromptTemplate promptTemplate = PromptTemplate.from(myTemplate);
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("topic", "baking a cake");
		variables.put("student_type", "java_programmer");
		
		Prompt prompt = promptTemplate.apply(variables);
				
        String response = cmodel.chat(prompt.text());

        System.out.println(response);		

	}

}

/* Output
Baking a cake is similar to writing a program in Java, where you follow a structured process to achieve a desired outcome. 
First, gather your ingredients, akin to importing necessary libraries, ensuring you have everything you need, like flour, sugar, eggs, 
and baking powder. Preheat your oven, much like setting up your development environment. Mix the ingredients according to the recipe, 
similar to writing your code with the correct syntax and logic. Pour the batter into a prepared pan, analogous to compiling your code. 
Place it in the oven and bake for the specified time, akin to running your program and waiting for execution. 
Finally, test the cake by inserting a toothpick to check if it comes out clean, just as you would debug and test your 
program to ensure it runs correctly. Once done, let it cool and enjoy the results, much like deploying your application for users to enjoy.

*/