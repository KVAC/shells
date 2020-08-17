package com.github.kvac.shells.shells.server.network.bots;

import com.github.kvac.shells.shells.server.header.SERVERHEADER;
import java.io.IOException;
import java.net.InetSocketAddress;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;

public class ServerBots {

    public void init() {
        SERVERHEADER.getAcceptor().getFilterChain().addLast("logger", new LoggingFilter());

        ObjectSerializationCodecFactory objectSerializationCodecFactory = new ObjectSerializationCodecFactory();
        objectSerializationCodecFactory.setDecoderMaxObjectSize(Integer.MAX_VALUE);
        objectSerializationCodecFactory.setEncoderMaxObjectSize(Integer.MAX_VALUE);
        SERVERHEADER.getAcceptor().getFilterChain().addLast("codec-Serializable", new ProtocolCodecFilter(objectSerializationCodecFactory));

        SERVERHEADER.getAcceptor().getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
        SERVERHEADER.getAcceptor().setHandler(SERVERHEADER.getMINA_BOTS_HANDLER());
    }

    public void bind() throws IOException {
        SERVERHEADER.getAcceptor().bind(new InetSocketAddress(SERVERHEADER.getPORT_FOR_BOTS()));
    }
}
