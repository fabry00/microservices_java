package com.mycompany.accountservice.dao.impl.inmemory;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Objects;

public class MyUserDB implements Principal {

    private final BigDecimal id;
    private final String name;
    private final String password;

    public MyUserDB(BigDecimal id, String name,String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public BigDecimal getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    

    @Override
    public String toString() {
        return "MyUser{" +
          "id='" + id + '\'' +
          ", name='" + name + '\'' +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MyUserDB myUser = (MyUserDB) o;
        return Objects.equals(id, myUser.id) && Objects.equals(name, myUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    
    public static class Builder{
    
        private BigDecimal id;
        private String name;
        private String password;
        public Builder withName(String name) {
            this.name = name;
            return this;
        };
        
        public Builder withPassword(String password) {
            this.password = password;
            return this;
        };
        
        public Builder withID(BigDecimal id) {
            this.id = id;
            return this;
        };
        
        public MyUserDB build() {
            MyUserDB user = new MyUserDB(id, name, password);
            return user;
        }
    }
}