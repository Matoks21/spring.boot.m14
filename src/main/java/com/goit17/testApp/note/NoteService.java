package com.goit17.testApp.note;


import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Setter
@Service
public class NoteService {
    private final List<Note> notes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Note> listAll() {
        return new ArrayList<>(notes);
    }

    public Note add(Note note) {
        long id = idGenerator.incrementAndGet();
        note.setId(id);
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        Optional<Note> noteOpt = notes.stream().filter(note -> note.getId() == id).findFirst();
        if (noteOpt.isPresent()) {
            notes.remove(noteOpt.get());
        } else {
            throw new IllegalArgumentException("Note with id " + id + " not found.");
        }
    }

    public void update(Note note) {
        Optional<Note> noteOpt = notes.stream().filter(n -> n.getId().equals(note.getId())).findFirst();
        if (noteOpt.isPresent()) {
            Note existingNote = noteOpt.get();
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
        } else {
            throw new IllegalArgumentException("Note with id " + note.getId() + " not found.");
        }
    }

    public Note getById(long id) {
        return notes.stream().filter(note -> note.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note with id " + id + " not found."));
    }
}
