package com.angelina.bespalaya_apteka.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; // аннотация для работы со столбцами в скл, id указывается автоматически
import jakarta.persistence.GenerationType; // класс отвечающих за тип данных перечисление
import jakarta.persistence.Id; // указание на первичный ключ

@Entity //аннотация, укзание на то что класс является сущностью и относится к orm jpa
public class Medicine {
    private Long id;
    private String title;
    private String compound;
    private String method;
    private String issueDate;
    private String returnDate;
    public Medicine(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getCompound(){return compound;}
    public void setCompound(String compound){this.compound = compound;}
    public String getMethod(){return method;}
    public void setMethod(String method ){this.method = method;}
    public String getIssueDate() {return issueDate;}
    public void setIssueDate(String issueDate){this.issueDate= issueDate;}
    public String getReturnDate() {return returnDate;}
    public void setReturnDate(String returnDate){this.returnDate = returnDate;}
}