package com.example.HTGOIT_2_15.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import jakarta.annotation.PostConstruct;

@Service
public class NoteServiceImp implements NoteService {
    private final List<Note> notes = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @PostConstruct
    public void init() {
        // Додавання 5 нотаток після створення біну
        notes.add(new Note(counter.incrementAndGet(), "Note 1", "Content for note 1"));
        notes.add(new Note(counter.incrementAndGet(), "Note 2", "Content for note 2"));
        notes.add(new Note(counter.incrementAndGet(), "Note 3", "Content for note 3"));
        notes.add(new Note(counter.incrementAndGet(), "Note 4", "Content for note 4"));
        notes.add(new Note(counter.incrementAndGet(), "Note 5", "Content for note 5"));
    }
    @Override
    public List<Note> listAll() {
        return notes;
    }

    @Override
    public Note add(Note note) {
        note.setId(counter.incrementAndGet());
        notes.add(note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        Optional<Note> noteToDelete = notes.stream().filter(note -> note.getId() == id).findFirst();
        if (noteToDelete.isPresent()) {
            notes.remove(noteToDelete.get());
        }
        else {
            throw new RuntimeException("Note not found");
        }
    }

    @Override
    public void update(Note note) {
        Optional<Note> noteToUpdate = notes.stream().filter(n -> n.getId() == note.getId())
                .findFirst();
        if (noteToUpdate.isPresent()) {
            noteToUpdate.get().setTitle(note.getTitle());
            noteToUpdate.get().setContent(note.getContent());
        }
        else {
            throw new RuntimeException("Note not found");
        }
    }

    @Override
    public Note getById(long id) {
        return notes.stream().filter(note -> note.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }
}
