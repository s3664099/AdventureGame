/* Being
 * Created: 23 March 2024
 * Updated: 23 March 2024
 * Version: 0.0
 * Class for creatures.
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class Being extends AbstractItem implements Item,Serializable {
	
	Conversation conversation;
	
	public Being(String name, String description, Conversation conversation) {
		super(name,description);
		this.conversation = conversation;
	}
	
	public Conversation talk() {
		return conversation;
	}

	@Override
	public String[] getNouns() {
		
		return super.getNouns();
	}

	@Override
	public String getName() {
		return super.getName();
	}

	//Methods for items that are closeable and lockable
	@Override
	public boolean getCloseable() {

		return false;
	}

	@Override
	public boolean getClosed() {
		
		return false;
	}

	@Override
	public boolean getLockable() {
		
		return false;
	}

	@Override
	public boolean getLocked() {
		
		return false;
	}

	@Override
	public void setClosed() {		
	}

	@Override
	public void setLocked() {		
	}

	@Override
	public boolean checkKey(Item key) {
		return false;
	}

	@Override
	public boolean getMoveable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Exit getHiddenExit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getHiddenItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getMoved() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMoved() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkHiddenExits() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkHiddenItems() {
		// TODO Auto-generated method stub
		return false;
	}

}

/* 23 March 2024 - Created file
*/
