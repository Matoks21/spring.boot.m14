package com.goit17.testApp.note;

import application.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
class NoteServiceTest {

    @Autowired
    private NoteService noteService;


    @BeforeEach
    public void setup() {
        noteService.listAll().forEach(note -> noteService.deleteById(note.getId()));
    }

    @Test
    void testAddNote() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("This is a test note.");
        Note savedNote = noteService.add(note);

        assertNotNull(savedNote.getId());
        assertEquals("Test Note", savedNote.getTitle());
        assertEquals("This is a test note.", savedNote.getContent());
    }

    @Test
    void testListAllNotes() {
        Note note1 = new Note();
        note1.setTitle("Note 1");
        note1.setContent("Content 1");

        Note note2 = new Note();
        note2.setTitle("Note 2");
        note2.setContent("Content 2");

        noteService.add(note1);
        noteService.add(note2);

        List<Note> notes = noteService.listAll();
        assertEquals(2, notes.size());
    }

    @Test
    void testGetById() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("This is a test note.");
        Note savedNote = noteService.add(note);

        Note fetchedNote = noteService.getById(savedNote.getId());
        assertNotNull(fetchedNote);
        assertEquals(savedNote.getId(), fetchedNote.getId());
        assertEquals("Test Note", fetchedNote.getTitle());
        assertEquals("This is a test note.", fetchedNote.getContent());
    }

    @Test
    void testUpdateNote() {
        Note note = new Note();
        note.setTitle("Old Title");
        note.setContent("Old Content");
        Note savedNote = noteService.add(note);

        savedNote.setTitle("New Title");
        savedNote.setContent("New Content");
        noteService.update(savedNote);

        Note updatedNote = noteService.getById(savedNote.getId());
        assertEquals("New Title", updatedNote.getTitle());
        assertEquals("New Content", updatedNote.getContent());
    }

    @Test
    void testDeleteById() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("This is a test note.");
        Note savedNote = noteService.add(note);

        noteService.deleteById(savedNote.getId());

        Long noteId = savedNote.getId();
        assertThrows(IllegalArgumentException.class, () -> {
            noteService.getById(noteId);
        });
    }
}