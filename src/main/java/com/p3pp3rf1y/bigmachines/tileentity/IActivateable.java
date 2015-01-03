package com.p3pp3rf1y.bigmachines.tileentity;

import com.p3pp3rf1y.beefcore.common.CoordTriplet;

/**
 * Implement this on tile entities which can be activated
 * @author Erogenous Beef
 */
public interface IActivateable {

	/**
	 * @return The coordinate at which your entities resides,
	 * or the reference coordinate of your multiblock.
	 */
	public CoordTriplet getReferenceCoord();
	
	/**
	 * @return True if your entity is active
	 */
	public boolean getActive();
	
	/**
	 * @param active Whether the user wishes this entity to be active
	 */
	public void setActive(boolean active);
	
}
