package com.example.serobeta0;

public class Post {

    public String name, ques, desc;

    public Post() {}

    public Post(String name, String ques, String desc) {
        this.name = name;
        this.ques = ques;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
