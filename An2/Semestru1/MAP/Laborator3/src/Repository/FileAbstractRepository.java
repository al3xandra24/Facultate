package Repository;

import Domain.Entity;
import Exceptions.RepositoryExceptions;
import Validator.Validator;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public abstract class FileAbstractRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    private String fileName;
    public FileAbstractRepository(String fileName, Validator<E> validator_) {
        super(validator_);
        this.fileName = fileName;
        loadData();
    }
    protected abstract E extractEntity(List<String> attributes);
    protected abstract String createEntityasString (E entity);
    private void loadData()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine())!=null){
                if (line.trim().isEmpty()) {
                    continue;
                }
                List<String> attributes = Arrays.asList(line.split(";"));
                E entity = extractEntity(attributes);
                super.save(entity);
            }
        } catch (IOException e)
            {
            throw new RepositoryExceptions("Error reading file!"+ fileName);
            }
    }
    private void saveData()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (E entity: super.findAll()) {
                String entityAsString = createEntityasString(entity);
                bw.write(entityAsString);
                bw.newLine();
            }
        } catch (IOException e){
            throw new RepositoryExceptions("Error writing file!"+ fileName);
        }
    }
    @Override
    public E save(E entity) {
        E entityToSave = super.save(entity);
        saveData();
        return entityToSave;
    }
    @Override
    public E delete(ID id) {
        E entityToDelete = super.delete(id);
        saveData();
        return entityToDelete;
    }
    @Override
    public E update(E entity) {
        E entityToUpdate = super.update(entity);
        saveData();
        return entityToUpdate;
    }
}
