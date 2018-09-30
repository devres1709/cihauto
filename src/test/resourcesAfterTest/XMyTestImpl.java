package ru.atc.oms.cih.services.orderingactivities;
import amdocs.oms.cih.services.base.CihStatelessServiceBase;
import amdocs.oms.infra.ErrorCode;
import amdocs.oms.oem.OmOrder;
import amdocs.oms.osmediation.MlCustomer;
import com.amdocs.cih.exception.CihBaseException;
import ru.atc.cih.datatype.general.MyGetAddressIdByOrderIdInput;
import ru.atc.cih.datatype.general.MyGetAddressIdByOrderIdOutput;
import ru.atc.oms.common.dao.XOrderDao;
import ru.atc.oms.utils.XUtils;


public class XMyTestImpl extends CihStatelessServiceBase {

    private XMyFirstDataType XMyFirstDataTypeprivate XMySecondDataType XMySecondDataType

    
public XMyFirstDataType getXMyFirstDataType() {
        return XMyFirstDataType;
    }public XMySecondDataType getXMySecondDataType() {
        return XMySecondDataType;
    }

    public void set XMyFirstDataType myXMyFirstDataType) {
this.XMyFirstDataType = XMyFirstDataType}public void set XMySecondDataType myXMySecondDataType) {
this.XMySecondDataType = XMySecondDataType}

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
