package com.github.kvac.shells.shells.bot;

import com.github.kvac.shells.shells.bot.header.SHELLHEADER;
import lombok.Getter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RShell {

    @Getter
    protected final Logger loggerJ = LoggerFactory.getLogger(getClass());

    public void init() {
        SHELLHEADER.getNIOCONNECTOR().getFilterChain().addLast("logger", new LoggingFilter());
        ObjectSerializationCodecFactory objectSerializationCodecFactory = new ObjectSerializationCodecFactory();
        objectSerializationCodecFactory.setDecoderMaxObjectSize(Integer.MAX_VALUE);
        objectSerializationCodecFactory.setEncoderMaxObjectSize(Integer.MAX_VALUE);
        SHELLHEADER.getNIOCONNECTOR().getFilterChain().addLast("codec-Serializable", new ProtocolCodecFilter(objectSerializationCodecFactory));
        SHELLHEADER.getNIOCONNECTOR().setConnectTimeoutMillis(30 * 1000);
        SHELLHEADER.getNIOCONNECTOR().setHandler(SHELLHEADER.getMCH());
        System.out.println("com.github.kvac.minecraft.minashells.RShell.init()");
    }

    public void start() {
        //TODO START THREADS
        SHELLHEADER.getCONNECTOR().start();
        System.out.println("com.github.kvac.minecraft.minashells.RShell.start()");
    }

    public void stop() {
        SHELLHEADER.getSTOP_EVENT_BUS().post("STOP");
        //TODO KILL ALL THREADS
        System.out.println("com.github.kvac.minecraft.minashells.RShell.stop()");
    }

}
