package com.desafio.task.controller;

import com.desafio.task.mappers.TaskMapper;
import com.desafio.task.dto.GenericResponse;
import com.desafio.task.dto.TaskDTO;
import com.desafio.task.entity.Task;
import com.desafio.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private TaskMapper mapper;

    @PostMapping("save")
    public ResponseEntity<GenericResponse<TaskDTO>> save(@RequestBody TaskDTO taskDto) throws Exception{
        Task obj = service.save(mapper.convertToEntity(taskDto));

        return new ResponseEntity<>(new GenericResponse<>(201, "success", Arrays.asList(mapper.convertToDto(obj))), HttpStatus.CREATED);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<TaskDTO>> readAll() throws Exception{
        List<TaskDTO> list = service.readAll().stream().map(this.mapper::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO dto, @PathVariable("id") Long id) throws Exception{
        Task obj = service.update(this.mapper.convertToEntity(dto), id);

        return ResponseEntity.ok(this.mapper.convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception{
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
