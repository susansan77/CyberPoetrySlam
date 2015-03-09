package broker.handler;

import broker.BrokerClient;

/**
 * 
 * There may be multiple swaps coming in. Each has its own ID.
 * If a client is already engaged in a swap, then subsequent swaps are denied 
 * immediately.
 * 
 * 		//2:verb:verb:ask:beat:verb:verb:ask:beat:
		//valid case:1.(verb,ask)->(verb,beat) ok    
	//valid case2:(verb,*)->(verb,beat) ok
	//valid case3:(*,*)->(verb,beat) ok
	//valid case4:(verb,ask)->(verb,*) ok
	//valid case5:(verb,ask)->(*,*)ok
	//valid case6:(verb,ask,adj,sweet)->(verb,beat,adj,bitter)ok
	//valid case7:(verb,ask,*,*)->(verb,beat,adj,bitter)ok
	//valid case8:(*,*,*,*)->(verb,beat,adj,bitter)ok
	//valid case9:(verb,ask,adj,*)->(verb,beat,adj,bitter)ok
	//valid case10:(verb,ask,adj,sweet)->(verb,beat,adj,*)ok
	//valid case11:(verb,ask,adj,sweet)->(verb,beat,*,*)ok
    //valid case12:(verb,ask,adj,sweet)->(*,*,*,*)ok
    //valid case13:(verb,ask,adj,sweet,verb,beat,adj,bitter,noun,sausage)->(pron,we,pron,I,prep,about,prep,after,noun,life)
	//failed case 1:find no words in offer(reqController)
	//failed case 2:find no words in reqword(brokermanager)
	//failed case 3:number!=reqWords.size
 * 
 * 
 */
public interface IHandleBrokerMessage {
	
	/** Handle message which was received from broker. */
	void process (BrokerClient broker, String msg);
	
	/** Handle when broker is lost independently. */
	void brokerGone();
}
