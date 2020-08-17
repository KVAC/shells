package com.github.kvac.shells.shells.server;

import com.github.kvac.shells.shells.server.header.SERVERHEADER;

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
