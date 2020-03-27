package com.fortinet.fpc.todo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="todo_item")
public class TodoItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="create_time")
    private String createTime;

    @Column(name="modified_time")
    private String modifiedTime;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "todoitem_id")
    private List<Status> statuses;

    public TodoItem() {
    }

    public TodoItem( String createTime, String modifiedTime, String title, String content, List<Status> statuses) {
        this.modifiedTime = modifiedTime;
        this.createTime = createTime;
        this.title = title;
        this.content = content;
        this.statuses = statuses;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public void add(Status tempStatus) {
        if(statuses == null) {
            statuses = new ArrayList<Status>();
        }

        statuses.add(tempStatus);
    }
}
