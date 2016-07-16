package com.mycompany.accountservice.api;

import com.fasterxml.jackson.annotation.JsonProperty;

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
