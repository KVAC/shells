package com.github.kvac.shells.shells.bot.network;

import lombok.Getter;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jdcs_dev
 */
public class MinaConnectionHandler extends IoHandlerAdapter {

    @Getter
    protected final Logger loggerJ = LoggerFactory.getLogger(getClass());

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.err.println(new String(message.toString().getBytes()));
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        super.sessionOpened(session);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

}
