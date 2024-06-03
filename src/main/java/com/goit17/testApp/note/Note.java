package com.goit17.testApp.note;

import lombok.Data;

@Data
public class Note {

    private Long id;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}