package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String typ;

    @Column(length = 5000000)
    private byte[] bytes;

    public Image() {
    }

    public Image(String name, String typ, byte[] bytes) {
        this.name = name;
        this.typ = typ;
        this.bytes = bytes;
    }

    public Image(Long id, String name, String typ, byte[] bytes) {
        this.id = id;
        this.name = name;
        this.typ = typ;
        this.bytes = bytes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
