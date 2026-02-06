package systemusrloop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

public class SysUserLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       Scanner userinput;
	       String cmdline;
	       List<ChatMessage> messages;
	       
			String apiKey = System.getenv("OPENAI_API_KEY");

   	       ChatModel model = OpenAiChatModel.builder()
	               .apiKey(apiKey)
	               .modelName(OpenAiChatModelName.GPT_4_O)
	               .build();


	       while (true) {
	       	messages = new ArrayList<>();

	           System.out.print("Instruction> ");
	           userinput = new Scanner(System.in);
	           cmdline = userinput.nextLine();
	           if (cmdline.isBlank())       
	               continue;
	           SystemMessage sysmsg = new SystemMessage(cmdline);
	           messages.add(sysmsg);

	           System.out.print("Question> ");
	           userinput = new Scanner(System.in);
	           cmdline = userinput.nextLine();
	           if (cmdline.isBlank())       
	               continue;
	           UserMessage usrmsg = UserMessage.from(cmdline);
	           messages.add(usrmsg);

	           ChatResponse answer = model.chat(messages);
	           System.out.println(answer.aiMessage().text());
	       }
	}
}

/* Output
Instruction> You are a polite Java expert explaining concepts to a grammar school child.
Question> Why should I learn JavaEE?
Hey there! So, you know how when you're playing with building blocks, you can make all kinds of cool structures, like houses, towers, or even little towns? Learning JavaEE (which stands for Java Platform, Enterprise Edition) is kind of like having a special set of building blocks that help you create big and complex structures, but in the tech world!

JavaEE is used for making powerful programs that run on big computers, like those used in businesses and websites you visit. Here's why learning about it can be super cool:

1. **Building Big Web Apps:** Just like you build a model city with blocks, JavaEE helps people make big web applications that lots of people can use at the same time. This is important for things like online shopping sites or video streaming services.

2. **Teamwork:** Imagine if you and your friends all built parts of a city with your blocks. JavaEE has tools that help many developers work together on a project, making building big applications easier and more organized.

3. **Safe and Secure:** When building a pretend fortress with your blocks, you might want to make sure it’s protected. JavaEE has things like security features to keep web applications safe, kind of like putting a lock on a treasure chest!

4. **Handling Lots of Traffic:** Suppose your block town has a busy road with lots of cars (or toy cars!). JavaEE is like a special set of rules that help manage lots of user requests to a web application, ensuring everything runs smoothly, even when many people are visiting at once.

5. **Fun with Servers:** JavaEE runs on something called servers, which are like super computers that help manage everything. Learning JavaEE gives you a peek into how these big computers help run parts of the internet and support different applications.

Even if you’re not thinking about becoming a JavaEE expert right now, learning a little about it can be like discovering a new world of possibilities in the realm of computer science and technology. It's a bit like adding a new level to your block-building skills!
Instruction> 

*/