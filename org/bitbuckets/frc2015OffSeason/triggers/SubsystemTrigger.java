package org.bitbuckets.frc2015OffSeason.triggers;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.buttons.InternalButton;

public class SubsystemTrigger extends InternalButton{
	
	BooleanSupplier getActive;
	
	public SubsystemTrigger(BooleanSupplier getActive){
		super();
		this.getActive = getActive;
	}
	
	@Override
	public final boolean get(){
		this.setPressed(getActive.getAsBoolean());
		return super.get();
	}

}