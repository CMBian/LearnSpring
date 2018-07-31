package hello;
import java.util.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class TodoDB {
	private HashMap<String, Todo> todoList;
	public HashMap<String, Todo> getTodoList(){
		if(todoList == null){
			this.todoList = new HashMap<String, Todo>();
			Todo todo1 = new Todo(), todo2 = new Todo();
	        todo1.setId("1");
	        todo1.setDone(true);
	        todo1.setValue("java learning");
	        
	        todo2.setId("2");
	        todo2.setDone(false);
	        todo2.setValue("resturant learning");
			this.todoList.put(todo1.getId(),todo1);
			this.todoList.put(todo2.getId(), todo2);
		}
		return this.todoList;
	}
}
