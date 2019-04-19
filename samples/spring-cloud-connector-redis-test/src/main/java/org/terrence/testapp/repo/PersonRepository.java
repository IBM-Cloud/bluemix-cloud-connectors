package org.terrence.testapp.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.terrence.testapp.model.Person;

public class PersonRepository implements CrudRepository<Person, String> {
    public static final String PERSONS_KEY = "persons";

    private final HashOperations<String, String, Person> hashOps;

    public PersonRepository(RedisTemplate<String, Person> redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
    }

    @Override
    public <S extends Person> S save(S person) {
        if (person.getId() == null) {
            person.setId(UUID.randomUUID().toString());
        }

        hashOps.put(PERSONS_KEY, person.getId(), person);

        return person;
    }

    @Override
    public <S extends Person> Iterable<S> saveAll(Iterable<S> persons) {
        List<S> result = new ArrayList<>();

        for (S entity : persons) {
            save(entity);
            result.add(entity);
        }

        return result;
    }

    @Override
    public Optional<Person> findById(String id) {
        return Optional.ofNullable(hashOps.get(PERSONS_KEY, id));
    }

    @Override
    public boolean existsById(String id) {
        return hashOps.hasKey(PERSONS_KEY, id);
    }

    @Override
    public Iterable<Person> findAll() {
        return hashOps.values(PERSONS_KEY);
    }

    @Override
    public Iterable<Person> findAllById(Iterable<String> ids) {
        return hashOps.multiGet(PERSONS_KEY, convertIterableToList(ids));
    }

    @Override
    public long count() {
        return hashOps.keys(PERSONS_KEY).size();
    }

    @Override
    public void deleteById(String id) {
        hashOps.delete(PERSONS_KEY, id);
    }

    @Override
    public void delete(Person person) {
        hashOps.delete(PERSONS_KEY, person.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends Person> persons) {
        for (Person person : persons) {
            delete(person);
        }
    }

    @Override
    public void deleteAll() {
        Set<String> ids = hashOps.keys(PERSONS_KEY);
        for (String id : ids) {
            deleteById(id);
        }
    }

    private <T> List<T> convertIterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (T object : iterable) {
            list.add(object);
        }
        return list;
    }
}