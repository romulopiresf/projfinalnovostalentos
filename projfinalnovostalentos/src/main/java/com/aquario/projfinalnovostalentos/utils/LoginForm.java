package com.aquario.projfinalnovostalentos.utils;

public class LoginForm {
    private String email;
    private String senha;


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", senha='" + getSenha() + "'" +
            "}";
    }


}

