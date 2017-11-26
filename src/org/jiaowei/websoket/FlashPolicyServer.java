package org.jiaowei.websoket;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by alex on 15-12-8.
 */
public class FlashPolicyServer {
    private ServerSocket serverSocket;
    private static Thread serverThread;
    private int port;
    private static boolean listening = true;
    private Logger logger = Logger.getLogger(FlashPolicyServer.class);
    public FlashPolicyServer() {
        this(843);
    }
    public FlashPolicyServer(int port) {
        this.port = port;
    }
    public void start() {
        try {
            serverThread = new Thread(new Runnable() {
                public void run() {
                    try {

                        logger.info("FlashPolicyServer: Starting...");
                        serverSocket = new ServerSocket(port);

                        while (listening) {
                            final Socket socket = serverSocket.accept();

                            Thread t = new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        if (logger.isDebugEnabled()) {
                                            logger.info("FlashPolicyServer: Handling Request...");
                                        }
                                        logger.info("找不到啊，找不到啊9");
                                        socket.setSoTimeout(10000);
                                        InputStream in = socket.getInputStream();
                                        byte[] buffer = new byte[23];
                                        in.read(buffer,0,23);
                                        String s = new String(buffer);
                                        System.out.println(s);
                                        if(s.equals("<policy-file-request/>\0")){
//                                        if (in.read(buffer) != -1 && (new String(buffer, "ISO-8859-1")).startsWith("<policy-file-request/>")) {
                                        	logger.info("进来了 ");
                                            if (logger.isDebugEnabled()) {
                                                logger.info("PolicyServerServlet: Serving Policy File...");
                                            }
                                            OutputStream out = socket.getOutputStream();
                                            byte[] bytes = ("<?xml version=\"1.0\"? encoding = \"utf-8\">\n" +
                                                    "<!DOCTYPE cross-domain-policy SYSTEM \"http://www.macromedia.com/xml/dtds/cross-domain-policy.dtd\">\n" +
                                                    "<cross-domain-policy> \n" +
//                                                    "	<site-control permitted-cross-domain-policies=\"all\"/>\n"+
                                                    "   <site-control permitted-cross-domain-policies=\"master-only\"/>\n" +
                                                    "   <allow-access-from domain=\"*\" to-ports=\"*\" secure=\"false\" />\n" +
                                                    "</cross-domain-policy>\0").getBytes("UTF-8");
                                            out.write(bytes);
                                            String s2 = new String(bytes);
                                            System.out.println(s2);
                                            out.flush();
                                            in.close();
                                            out.close();
                                            logger.info( "完成认证");
                                        } else {
                                            logger.warn("FlashPolicyServer: Ignoring Invalid Request");
                                            logger.warn("  " + (new String(buffer)));
                                        }

                                    } catch (SocketException e) {
                                        logger.error(e.getMessage(), e);
                                    } catch (IOException e) {
                                        logger.error(e.getMessage(), e);
                                    } finally {
                                        try {
                                            socket.close();
                                        } catch (Exception ex2) {
                                        }
                                    }
                                }
                            });
                            t.start();
                        }
                    } catch (IOException ex) {
                        logger.error("PolicyServerServlet Error---");
                        logger.error(ex.getMessage(), ex);
                    }
                }
            });
            serverThread.start();
        } catch (Exception ex) {
            logger.error("PolicyServerServlet Error---");
            logger.error(ex.getMessage(), ex);
        }
    }

    public void stop() {
        logger.info("FlashPolicyServer: Shutting Down...");

        if (listening) {
            listening = false;
        }

        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (Exception ex) {
            }
        }
    }
}
