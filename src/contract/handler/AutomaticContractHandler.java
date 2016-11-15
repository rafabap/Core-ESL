package contract.handler;

import inventory.Good;
import contract.messages.ObligationResponse;
import contract.obligation.Obligation;

public class AutomaticContractHandler extends ContractHandler{

	@Override
	public ObligationResponse handleObligation(Obligation o) {

		if (o == null) {
			return new ObligationResponse(o, false);
		}
		if (o.getWhat() instanceof Good) {
			return new ObligationResponse(o, handleGood(o));
		} else {
			return new ObligationResponse(o, false);
		}
	}
	
	private Boolean handleGood(Obligation o) {
		
		Good g = (Good) o.getWhat();
		
		// attempt to pull a quantity of a good from a balancesheet
		try{
			o.getFrom().getInventory().remove(g);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		// give the good to a balancesheet
		o.getTo().getInventory().add(g);
		
		return true;
		
	}
}