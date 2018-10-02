package ru.atc.oms.cih.services.orderingactivities;

import com.amdocs.cih.exception.CihBaseException;
import ru.atc.cih.datatype.general.MyTestInput;
import ru.atc.cih.datatype.general.MyTestOutput;

import amdocs.oms.cih.services.base.CihStatelessServiceBase;
import amdocs.oms.infra.ErrorCode;

public class XMyTestClassImpl extends CihStatelessServiceBase {

    private MyTestInput myTestInput;
    private MyTestOutput myTestOutput;

    public MyTestInput getMyTestInput() {
        return myTestInput;
    }

    public MyTestOutput getMyTestOutput() {
        return myTestOutput;
    }

    public void setMyTestInput(MyTestInput myTestInput) {
        this.myTestInput = myTestInput;
    }

    public void setMyTestOutput(MyTestOutput myTestOutput) {
        this.myTestOutput = myTestOutput;
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
