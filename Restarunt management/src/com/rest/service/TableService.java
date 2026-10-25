package com.rest.service;

import com.rest.models.Table;
import com.rest.repository.TableRepo;
import java.util.List;

public class TableService {

    private final TableRepo repository;
    public TableService(){
        repository = new TableRepo();
    }
    public  List<Table> getAll(){
        return repository.show();
    }
    public void editTable(int id) {
        repository.EditTable(id);
    }

    public void deleteTable(int id) {
        repository.Deletetable(id);
    }
}