package com.github.kvac.shells.shells.bot.header;

import com.github.kvac.shells.shells.bot.network.MinaConnectionHandler;
import com.github.kvac.shells.shells.bot.proc.ProcessHandler;
import com.github.kvac.shells.shells.bot.RShell;
import com.github.kvac.shells.shells.bot.network.Connector;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class SHELLHEADER {

    @Getter
    static final ConcurrentHashMap<String, Integer> CAC = new ConcurrentHashMap<>();
    @Getter
    static final RShell R_SHELL = new RShell();
    @Getter
    static final NioSocketConnector NIOCONNECTOR = new NioSocketConnector();
    @Getter
    static final MinaConnectionHandler MCH = new MinaConnectionHandler();
    @Getter
    static final EventBus STOP_EVENT_BUS = new EventBus();
    @Getter
    static final Connector CONNECTOR = new Connector();

    @Getter
    static final CopyOnWriteArrayList PROCESSES_LIST = new CopyOnWriteArrayList();
    @Getter
    static final ProcessHandler PROCESS_HANDLER = new ProcessHandler();

}
