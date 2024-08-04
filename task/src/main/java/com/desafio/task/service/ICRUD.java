package com.desafio.task.service;

import java.util.List;

public interface  ICRUD<T, ID>{
        T save(T t) throws Exception;
        T update(T t, ID id) throws Exception;
        List<T> readAll() throws Exception;
        void delete(ID id) throws Exception;
}
