package com.example.krestnull3.db;



public class Data {
    public String firstname;
    public String secondname;
    public String result;
    public Data(String firstname , String secondname, String result){
        this.firstname=firstname;
        this.secondname=secondname;
        this.result=result;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getResult() {
        return result;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
