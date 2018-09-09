package com.jastrzab.chat.model;

public enum par {
    R_TYPE("RT"),
    ERROR_MES("ERM"),
    SUCCES("SU"),

    USER_ID("U_ID"),
    USER_NICK("U_N"),

    MES_FROM("MES_F"),
    MES_TO("MES_TO"),
    MES_CONTENT("MES_C");


    private String id;
    par(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }
    public String v(){
        return this.id;
    }


}
