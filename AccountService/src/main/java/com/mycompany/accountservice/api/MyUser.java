package com.mycompany.accountservice.api;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Objects;

public class MyUser implements Principal {

    private  BigDecimal id;
    private  String name;

    public MyUser() {
    }
    
    public MyUser(BigDecimal id, String name) {
        this.id = id;
        this.name = name;
    }

    public BigDecimal getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyUser{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MyUser myUser = (MyUser) o;
        return Objects.equals(id, myUser.id) && Objects.equals(name, myUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static class Builder {

        private BigDecimal id;
        private String name;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withID(BigDecimal id) {
            this.id = id;
            return this;
        }
        
        public MyUser build() {
            return new MyUser(id, name);
        }

    }
}
