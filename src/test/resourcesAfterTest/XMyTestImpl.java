package ru.atc.oms.cih.services.orderingactivities;

import ru.atc.cih.datatype.general.XMyFirstDataTypeInput
import ru.atc.cih.datatype.general.XMySecondDataTypeOutput


public class XMyTestImpl extends CihStatelessServiceBase {

    private XMyFirstDataTypeInput xMyFirstDataTypeInput;
	private XMySecondDataTypeOutput xMySecondDataTypeOutput;
	

    
	public XMyFirstDataTypeInput getXMyFirstDataTypeInput() { return xMyFirstDataTypeInput; }

	public XMySecondDataTypeOutput getXMySecondDataTypeOutput() { return xMySecondDataTypeOutput; }



    public void setXMyFirstDataTypeInput(XMyFirstDataTypeInput myXMyFirstDataTypeInput){
		this.xMyFirstDataTypeInput = xMyFirstDataTypeInput;
	}
	public void setXMySecondDataTypeOutput(XMySecondDataTypeOutput myXMySecondDataTypeOutput){
		this.xMySecondDataTypeOutput = xMySecondDataTypeOutput;
	}
	

    @Override
    protected ErrorCode executeImpl() throws CihBaseException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void inputMapping() throws CihBaseException {
        // TODO Auto-generated method stub
    }

    @Override
    protected Object outputMapping() throws CihBaseException {
        // TODO Auto-generated method stub
        return null;
    }
}
