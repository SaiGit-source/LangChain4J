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


/* Output
I simply said “them” instead of “Java records” in the third prompt and it knows from the context and gives correct response

prompt> Can I use it for Generative AI?
Absolutely, you can use Artificial Intelligence (AI) and Machine Learning (ML) techniques for Generative AI. 

Generative AI is a subset of AI that includes technologies capable of creating something new. It's a form of machine learning where AI models are trained to generate new data that are similar to a provided dataset. 

For instance, chatbots and automatic text generators use generative AI to produce sentences. It also plays a significant role in creating images, improving photo resolution, creating human-like voices, etc.

To use Generative AI, one typically involves technologies such as Generative Adversarial Networks (GANs) or Variational Autoencoders (VAEs) which are advanced ML algorithms that provide the ability for an AI to generate data. These methods can learn the essential characteristics of different types of data like images, music, voice, and even text, and then generate new, synthetic data that can pass for original data.

Of course, using these sophisticated technologies effectively will require a deep understanding of machine learning principles and techniques. If you're interested, I would be more than happy to guide your learning and help you leverage Generative AI in your work or projects.
prompt> What are Java records?
Java Record is a new feature introduced in Java 14 as a preview feature and finalized in Java 16. Records in Java provide a way to model plain data aggregates with less ceremony. 

A Java Record is a special type of class that can hold pure data. It's similar to a normal class, but with some key differences:
1. A record is an immutable class that automatically implements several useful interfaces, including equals(), hashCode(), and toString().
2. A Record's state is defined in the record header, and this state is both final and private. 
3. A record class will automatically create getter methods (but not setter, because it's immutable) for each of the variables defined in the record header, following the JavaBean convention.
4. Record classes implicitly declare a canonical constructor (one that takes all the fields), and it's not necessary to declare this manually.

Here is an example of a record:

```java
public record Employee(String firstName, String lastName, int employeeId) {
   // All other code, like methods or other logic, can go here
}
```

In this example, `Employee` is a record having a `firstName`, a `lastName`, and an `employeeId` as its state. And we get equals(), hashCode(), toString(), and corresponding getters automatically from the JVM. However, keep in mind that records are intended to be simple data carriers and may not be the best choice if you need more complex class behaviors.
prompt> Can I use them for GenAI applications?
Yes, Java Records can be used in Generative AI (GenAI) applications, provided you are using Java for your AI work.

Here's how: GenAI often involves processing large amounts of data. This data might be used for training models, generating new data, or other tasks. While the heavy lifting is typically done by machine learning libraries that may be written in languages best suited to numerical computing (like Python), there's often a significant amount of "glue code" and data handling code needed to support these tasks.

Java Records could be quite useful for handling this data. Because they are a way of creating simple, immutable data carrier classes, they could be used to model the data that you're processing. For example, if you're working with a dataset of images where each image has a label and a matrix of pixel data, you might create a Record to neatly encapsulate these two data items.

However, keep in mind that the core modeling, learning, and generation tasks in a GenAI workload are typically performed using specialized libraries (like TensorFlow, PyTorch, etc.) that have extensive support for the kind of high-performance numerical computing that these tasks require. Java is not typically the first choice of programming language for this part of a GenAI application, though it can serve an important role in the broader application architecture, for tasks like data preprocessing, system integration, etc.
prompt>

*/