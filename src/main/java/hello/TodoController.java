package hello;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "todo")
public class TodoController {
		private TodoDB todoDB;
		//@Autowired
		//private HttpSession session;
	    @GetMapping(value = "/list", produces = { "application/json;charset=UTF-8" })
	    public List<Todo> getAllTodos(HttpSession session) {
	    	//HttpSession session = request.getSession();
	    	this.todoDB = (session.getAttribute("todoDB")==null ? new TodoDB():(TodoDB)session.getAttribute("todoDB"));
	    	List<Todo> todoList = new ArrayList<Todo>();
	        this.todoDB.getTodoList().forEach((K, V)->{todoList.add(V);});
	        System.out.print("##############"+session.getAttribute("test"));
	        return todoList;
	    }
	    
	    
	    @PutMapping(value = "/{id}", produces = { "application/json;charset=UTF-8" })
	    public boolean updateTodo(@ModelAttribute("todoDB") TodoDB todoDB, @PathVariable String id, @RequestBody Todo todo) {
				Todo todo1 = new Todo();
				if(id == null){
					todo.setId(Utils.generateShortUuid());
				}
		        todo1.setId(todo.getId());
		        todo1.setDone(todo.isDone());
		        todo1.setValue(todo.getValue());
				todoDB.getTodoList().put(todo1.getId(),todo1);
	      return true;
	    }
	    @PostMapping(value = "", produces = { "application/json;charset=UTF-8" })
	    public boolean addTodo(@RequestBody Todo todo,HttpSession session) {
	    	
	    	this.todoDB = (session.getAttribute("todoDB")==null ? new TodoDB():(TodoDB)session.getAttribute("todoDB"));
			Todo todo1 = new Todo();
			//todo.setId(Integer.parseInt(Utils.generateShortUuid()));
	        todo1.setId(Utils.generateShortUuid());
	        todo1.setDone(todo.isDone());
	        todo1.setValue(todo.getValue());
	        this.todoDB.getTodoList().put(todo1.getId(),todo1);
	        session.setAttribute("todoDB", this.todoDB);
	        session.setAttribute("test", "test");
      return true;
    }
	    @DeleteMapping(value = "/{id}", produces = { "application/json;charset=UTF-8" })
	    public boolean delTodo(@RequestBody Todo employeeEntity) {
	      return true;
	    }
	    
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    class UserNotFoundException extends RuntimeException {

	    	public UserNotFoundException(String userId) {
	    		super("could not find user '" + userId + "'.");
	    	}
	    }

	}