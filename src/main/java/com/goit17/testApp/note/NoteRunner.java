package com.goit17.testApp.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NoteRunner implements CommandLineRunner {

    private final NoteService noteService;

   @Autowired
    public NoteRunner(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public void run(String... args) {
        Note note = new Note();
        note.setTitle("New note1");
        note.setContent("New contex1");

        noteService.add(note);
        System.out.println("Note added: " + note);

        Note note2 = new Note();
        note2.setTitle("New note2");
        note2.setContent("New contex2");

        noteService.add(note2);
        System.out.println("Note2 added: " + note2);

        note.setTitle("Update note");
        noteService.update(note);
        System.out.println("note = " + note);

        System.out.println("noteService.getById(1L) = " + noteService.getById(1L));

        noteService.deleteById(1L);

        System.out.println("noteService.listAll() = " + noteService.listAll());
    }
}
