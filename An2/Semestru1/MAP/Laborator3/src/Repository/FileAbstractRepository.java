package Repository;

import Domain.Entity;
import Exceptions.RepositoryExceptions;
import Exceptions.ValidationException;
import Validator.Validator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public abstract class FileAbstractRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    private String fileName;

    /**
     * Creates the repository and loads the entities from the file
     * @param fileName the path of the file
     * @param validator_ the validator used for every entity, includes the ones read from file
     */
    public FileAbstractRepository(String fileName, Validator<E> validator_) {
        super(validator_);
        this.fileName = fileName;
        loadData();
    }

    /**
     * Builds an entity from the fields of one of the file
     * @param attributes the fields of the line in the order they appear in the file
     * @return the entity described by the line
     * @throws NumberFormatException if a numeric field is not a number
     * @throws DateTimeParseException if a date fields does not have the expected format
     * @throws IndexOutOfBoundsException if the line has fewer fields than expected
     */
    protected abstract E extractEntity(List<String> attributes);

    /**
     *Converts an entity to the line that is written in the file.
     * @param entity the entity to convert
     * @return the line for the entity, with the fields separated by ";"
     */
    protected abstract String createEntityasString (E entity);

    private void loadData()
    {
        if (!Files.exists(Paths.get(fileName))) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine())!=null){
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                List<String> attributes = Arrays.asList(line.split(";"));
                E entity;
                try {
                    entity = extractEntity(attributes);
                    super.save(entity);
                }catch (NumberFormatException | DateTimeParseException | IndexOutOfBoundsException e){
                    System.err.println("Badly formatted data at line "+ lineNumber+ e.getMessage().trim());
                }catch (ValidationException e){
                    System.err.println("Invalid data at line "+ lineNumber+ e.getMessage().trim());
                }catch (RepositoryExceptions e){
                    System.err.println("Repository problem at line "+ lineNumber+ e.getMessage().trim());
                }

            }
        } catch (IOException e)
            {
            throw new RepositoryExceptions("Error reading file! "+ fileName, e);
            }
    }
    private void saveData()
    {
        try{
            Path parent=Path.of(fileName).toAbsolutePath().getParent();
            if (parent!=null) {
                Files.createDirectories(parent);
            }
        }catch (IOException e){
            throw new RepositoryExceptions("Error creating directory! "+ fileName, e);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (E entity: super.findAll()) {
                String entityAsString = createEntityasString(entity);
                bw.write(entityAsString);
                bw.newLine();
            }
        } catch (IOException e){
            throw new RepositoryExceptions("Error writing file!"+ fileName, e);
        }
    }
    /**
     * Saves a new entity
     * @param entity the entity to save
     * @return the saved entity
     * @throws IllegalArgumentException if the entity is null
     * @throws ValidationException if the entity is not valid
     * @throws RepositoryExceptions if an entity with the same ID already exists
     */
    @Override
    public E save(E entity) {
        E entityToSave = super.save(entity);
        saveData();
        return entityToSave;
    }

    /**
     * Deletes the entity with the given ID.
     * @param id the ID of the entity to delete
     * @return the deleted entity
     * @throws IllegalArgumentException if the ID is null
     * @throws RepositoryExceptions if no entity with this ID exists  or the data cannot be stored
     */
    @Override
    public E delete(ID id) {
        E entityToDelete = super.delete(id);
        saveData();
        return entityToDelete;
    }

    /**
     * Replaces an existing entity with the given one (the one with the same ID).
     * @param entity the entity with the new data
     * @return the updated entity
     * @throws IllegalArgumentException if the entity is null
     * @throws ValidationException if the entity is not valid
     * @throws RepositoryExceptions if no entity with this ID exists or the data cannot be stored
     */
    @Override
    public E update(E entity) {
        E entityToUpdate = super.update(entity);
        saveData();
        return entityToUpdate;
    }
}
