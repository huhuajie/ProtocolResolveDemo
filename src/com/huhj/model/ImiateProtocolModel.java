package com.huhj.model;

import java.math.BigDecimal;

import com.huhj.resolve.annotation.Convert;
import com.huhj.resolve.annotation.Replace;
import com.huhj.resolve.annotation.Replace.ReplaceOperation;
import com.huhj.resolve.annotation.Validation;
import com.huhj.resolve.annotation.Convert.ConvertOperation;
import com.huhj.resolve.annotation.Validation.ValidationOperation;

@Validation(start=0,end=1,operation=ValidationOperation.HEXEQUALS,parameters={"3F"})
@Validation(start=-1,end=0,operation=ValidationOperation.HEXEQUALS,parameters={"3B"})
@Replace(start=3,end=5,operation=ReplaceOperation.Subtract,parameters={"11"})
public class ImiateProtocolModel {
	
	@Convert(start=3,end=5,operation=ConvertOperation.HexDecimal,parameters={"2"})
	private BigDecimal voltage;

	public BigDecimal getVoltage() {
		return voltage;
	}

	public void setVoltage(BigDecimal voltage) {
		this.voltage = voltage;
	}

	@Override
	public String toString() {
		return "ImiateProtocolModel [voltage=" + voltage + "]";
	}
	
	

}
