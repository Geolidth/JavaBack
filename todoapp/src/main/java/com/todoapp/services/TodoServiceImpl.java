package com.todoapp.services;

import com.todoapp.exceptions.ForbiddenActionException;
import com.todoapp.exceptions.NoSuchTodoException;
import com.todoapp.exceptions.NoSuchUserException;
import com.todoapp.models.dao.Todo;
import com.todoapp.models.dao.TodoUser;
import com.todoapp.models.dto.UpdateTodo;
import com.todoapp.models.dto.newTodo;
import com.todoapp.repositories.TodoRepositroy;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    private UserService userService;

    private TodoRepositroy todoRepositroy;

    @Override
    public List<Todo> getTodos() {
        return todoRepositroy.findAll();
    }

    @Override
    public List<Todo> getTodos(Optional<Boolean> isDone, Optional<String> q) {
        if(isDone.isEmpty() && q.isEmpty()) {
            return getTodos();
        }else if (isDone.isPresent() && q.isEmpty()){
            return todoRepositroy.findAllByIsDone(isDone.get());
        }else if (q.isPresent() && isDone.isEmpty()){
            return todoRepositroy.findAllByTitleContainsIgnoreCase(q.get().toLowerCase());
        }else{
            return todoRepositroy.findAllByIsDoneAndTitleContainsIgnoreCase(isDone.get(), q.get());
        }
    }

    @Override
    public Todo getById(Integer id) throws NoSuchTodoException {
        return todoRepositroy.findById(id).orElseThrow(NoSuchTodoException::new);
    }

    @Override
    public Todo save(Integer userId, newTodo newTodo) throws NoSuchUserException {
        Todo todo= convertToTodo(userId, newTodo);
        return todoRepositroy.save(todo);
    }

    @Override
    public Todo update(Integer userId, Integer todoId, UpdateTodo update)
            throws NoSuchUserException, NoSuchTodoException, ForbiddenActionException{
        TodoUser user= userService.getById(userId);
        Optional<Todo> todoOptional = todoRepositroy.findById(todoId);
        if(todoOptional.isPresent()){
            if(!todoOptional.get().getOwner().getId().equals(user.getId())){
                throw new ForbiddenActionException();
            }
          Todo todo = todoOptional.get();
          if(update.getTitle()!= null&& !update.getTitle().isBlank()) {
              todo.setTitle(update.getTitle());
          }
          if(update.getIsDone()!=null){
            todo.setDone(update.getIsDone());
          }
          return todoRepositroy.save(todo);
        }else{
            throw new NoSuchTodoException();
        }
    }

    @Override
    public void delete(Integer userId, Integer todoId)
            throws NoSuchUserException, NoSuchTodoException, ForbiddenActionException{
        TodoUser user = userService.getById(userId);
        Optional<Todo> todo = todoRepositroy.findById(todoId);
        if(todo.isPresent()) {
            if(todo.get().getOwner().getId().equals(userId)){
            todoRepositroy.deleteById(todoId);
            }else{
                throw new ForbiddenActionException();
            }
        } else{
            throw new NoSuchTodoException();
        }
    }

    public Todo convertToTodo(Integer userId, newTodo newTodo) throws NoSuchUserException{
        TodoUser user = userService.getById(userId);
        Todo todo= new Todo(newTodo.getTitle(), false);
        todo.setOwner(user);
        return todo;
    }
}
