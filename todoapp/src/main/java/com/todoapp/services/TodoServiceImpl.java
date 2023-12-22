package com.todoapp.services;

import com.todoapp.exceptions.NoSuchTodoException;
import com.todoapp.models.dao.Todo;
import com.todoapp.models.dto.UpdateTodo;
import com.todoapp.models.dto.newTodo;
import com.todoapp.repositories.TodoRepositroy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepositroy todoRepositroy;
    public TodoServiceImpl(TodoRepositroy todoRepositroy) {
        this.todoRepositroy=todoRepositroy;
    }
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
    public Todo save(newTodo newTodo) {
        Todo todo= new Todo(newTodo.getTitle(), false);
        return todoRepositroy.save(todo);
    }

    @Override
    public Todo update(Integer id, UpdateTodo update) throws NoSuchTodoException{
        Optional<Todo> todoOptional = todoRepositroy.findById(id);
        if(todoOptional.isPresent()){
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
    public void delete(Integer id) throws NoSuchTodoException {
        if(todoRepositroy.existsById(id)) {
            todoRepositroy.deleteById(id);
        } else{
            throw new NoSuchTodoException();
        }
    }
}
