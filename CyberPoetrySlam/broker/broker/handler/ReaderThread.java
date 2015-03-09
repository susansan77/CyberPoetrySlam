package broker.handler;

import java.io.BufferedReader;

import broker.BrokerClient;

/** 
 * Thread to process each request from the broker.
 * 
 * Blocks until a request comes in.
 *  
 */
public class ReaderThread extends Thread {
	
	/** Broker from whom we are reading messages. */
	final BrokerClient broker;
	final IHandleBrokerMessage handler;
	
	/** Active until this is told to stop. */
	boolean stopped = false;
	
	public ReaderThread(BrokerClient b, IHandleBrokerMessage handler) {
		broker = b;
		this.handler = handler;
	}
	
	/** Send message to the broker. */
	public void sendMessage(String msg) {
		broker.getBrokerOutput().println(msg);
	}
	
	/**
	 * Terminate the reader cleanly to avoid dangling threads
	 */
	public void shutdown () {
	
		stopped = true;
	}
	
	public void run ()  {
		try {	
			
			BufferedReader incoming = broker.getBrokerInput();
			//System.out.println("incoming has value"+incoming.readLine());
			//System.out.println(!stopped);
			while (!stopped) {
				//System.out.println("incoming execute");
				String cmd = incoming.readLine();
				System.out.println(cmd);
				handler.process(broker, cmd);
			}
		} catch (Exception ioe) {
			// server down for some reason. Should we reconnect?
			broker.shutdown();
			handler.brokerGone();
		}
	}
}
