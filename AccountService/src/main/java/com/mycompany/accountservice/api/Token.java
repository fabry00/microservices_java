package com.mycompany.accountservice.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Token {

    public enum Status {
        VALID,
        INVALID,
        EXPIRED
    };

    private Status status;
    private String token;

    @JsonProperty
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Token tokenOther = (Token) o;
        return Objects.equals(this.token, tokenOther.token) && 
                Objects.equals(this.status, tokenOther.status);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.status);
        hash = 29 * hash + Objects.hashCode(this.token);
        return hash;
    }

    public static class Builder {

        private Status status;
        private String token;

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Token build() {
            Token tokenObj = new Token();
            tokenObj.setStatus(status);
            tokenObj.setToken(token);
            return tokenObj;
        }
    }
}
