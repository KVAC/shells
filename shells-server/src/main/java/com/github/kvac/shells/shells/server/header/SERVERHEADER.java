package com.github.kvac.shells.shells.server.header;

import com.github.kvac.shells.shells.server.network.MinaBotsHandler;
import com.github.kvac.shells.shells.server.network.bots.ServerBots;
import lombok.Getter;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class SERVERHEADER {

    @Getter
    static final int PORT_FOR_BOTS = 10001;
    @Getter
    static final int PORT_FOR_CLIENTS = 10002;

    @Getter
    static final ServerBots SERVER_FOR_BOTS = new ServerBots();
    @Getter
    static final IoAcceptor acceptor = new NioSocketAcceptor();

    @Getter
    static final MinaBotsHandler MINA_BOTS_HANDLER = new MinaBotsHandler();
}
