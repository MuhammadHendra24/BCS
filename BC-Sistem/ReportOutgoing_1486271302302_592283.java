/*
 * Generated by JasperReports - 2/5/17 12:08 PM
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.net.*;



/**
 *
 */
public class ReportOutgoing_1486271302302_592283 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_FILTER = null;
    private JRFillParameter parameter_JASPER_REPORT = null;
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_tgl1 = null;
    private JRFillParameter parameter_JASPER_REPORTS_CONTEXT = null;
    private JRFillParameter parameter_REPORT_FILE_RESOLVER = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillParameter parameter_tgl = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_REPORT_CONTEXT = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_REPORT_URL_HANDLER_FACTORY = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_SORT_FIELDS = null;
    private JRFillField field_ItemCode = null;
    private JRFillField field_DateNoDoc = null;
    private JRFillField field_Description = null;
    private JRFillField field_Customer = null;
    private JRFillField field_CreatorId = null;
    private JRFillField field_NoInvoice = null;
    private JRFillField field_DateNoDo = null;
    private JRFillField field_NoDoc = null;
    private JRFillField field_NoDo = null;
    private JRFillField field_ShortDocument = null;
    private JRFillField field_Date = null;
    private JRFillField field_GWeight = null;
    private JRFillField field_Price = null;
    private JRFillField field_GoodQty = null;
    private JRFillField field_Currency = null;
    private JRFillField field_UM = null;
    private JRFillField field_Id = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_MASTER_CURRENT_PAGE = null;
    private JRFillVariable variable_MASTER_TOTAL_PAGES = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;


    /**
     *
     */
    public void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    private void initParams(Map pm)
    {
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_FILTER = (JRFillParameter)pm.get("FILTER");
        parameter_JASPER_REPORT = (JRFillParameter)pm.get("JASPER_REPORT");
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_tgl1 = (JRFillParameter)pm.get("tgl1");
        parameter_JASPER_REPORTS_CONTEXT = (JRFillParameter)pm.get("JASPER_REPORTS_CONTEXT");
        parameter_REPORT_FILE_RESOLVER = (JRFillParameter)pm.get("REPORT_FILE_RESOLVER");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
        parameter_tgl = (JRFillParameter)pm.get("tgl");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_REPORT_CONTEXT = (JRFillParameter)pm.get("REPORT_CONTEXT");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_REPORT_URL_HANDLER_FACTORY = (JRFillParameter)pm.get("REPORT_URL_HANDLER_FACTORY");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_SORT_FIELDS = (JRFillParameter)pm.get("SORT_FIELDS");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
        field_ItemCode = (JRFillField)fm.get("ItemCode");
        field_DateNoDoc = (JRFillField)fm.get("DateNoDoc");
        field_Description = (JRFillField)fm.get("Description");
        field_Customer = (JRFillField)fm.get("Customer");
        field_CreatorId = (JRFillField)fm.get("CreatorId");
        field_NoInvoice = (JRFillField)fm.get("NoInvoice");
        field_DateNoDo = (JRFillField)fm.get("DateNoDo");
        field_NoDoc = (JRFillField)fm.get("NoDoc");
        field_NoDo = (JRFillField)fm.get("NoDo");
        field_ShortDocument = (JRFillField)fm.get("ShortDocument");
        field_Date = (JRFillField)fm.get("Date");
        field_GWeight = (JRFillField)fm.get("GWeight");
        field_Price = (JRFillField)fm.get("Price");
        field_GoodQty = (JRFillField)fm.get("GoodQty");
        field_Currency = (JRFillField)fm.get("Currency");
        field_UM = (JRFillField)fm.get("UM");
        field_Id = (JRFillField)fm.get("Id");
    }


    /**
     *
     */
    private void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_MASTER_CURRENT_PAGE = (JRFillVariable)vm.get("MASTER_CURRENT_PAGE");
        variable_MASTER_TOTAL_PAGES = (JRFillVariable)vm.get("MASTER_TOTAL_PAGES");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
    }


    /**
     *
     */
    public Object evaluate(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = ((java.sql.Date)parameter_tgl.getValue()); //$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = ((java.lang.String)field_ShortDocument.getValue()); //$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = ((java.lang.String)field_NoDoc.getValue()); //$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = ((java.lang.String)field_NoDo.getValue()); //$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = ((java.lang.String)field_DateNoDo.getValue()); //$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = ((java.lang.String)field_ItemCode.getValue()); //$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = ((java.lang.String)field_Description.getValue()); //$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = ((java.lang.String)field_UM.getValue()); //$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = ((java.lang.Double)field_Price.getValue()); //$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = ((java.lang.String)field_GoodQty.getValue()); //$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = ((java.sql.Date)field_DateNoDoc.getValue()); //$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = ((java.lang.String)field_Customer.getValue()); //$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = "Page " + ((java.lang.Integer)variable_PAGE_NUMBER.getValue()); //$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = new java.util.Date(); //$JR_EXPR_ID=21$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateOld(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = ((java.sql.Date)parameter_tgl.getValue()); //$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = ((java.lang.String)field_ShortDocument.getOldValue()); //$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = ((java.lang.String)field_NoDoc.getOldValue()); //$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = ((java.lang.String)field_NoDo.getOldValue()); //$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = ((java.lang.String)field_DateNoDo.getOldValue()); //$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = ((java.lang.String)field_ItemCode.getOldValue()); //$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = ((java.lang.String)field_Description.getOldValue()); //$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = ((java.lang.String)field_UM.getOldValue()); //$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = ((java.lang.Double)field_Price.getOldValue()); //$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = ((java.lang.String)field_GoodQty.getOldValue()); //$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = ((java.sql.Date)field_DateNoDoc.getOldValue()); //$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = ((java.lang.String)field_Customer.getOldValue()); //$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = "Page " + ((java.lang.Integer)variable_PAGE_NUMBER.getOldValue()); //$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = new java.util.Date(); //$JR_EXPR_ID=21$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateEstimated(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = ((java.sql.Date)parameter_tgl.getValue()); //$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = ((java.lang.String)field_ShortDocument.getValue()); //$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = ((java.lang.String)field_NoDoc.getValue()); //$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = ((java.lang.String)field_NoDo.getValue()); //$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = ((java.lang.String)field_DateNoDo.getValue()); //$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = ((java.lang.String)field_ItemCode.getValue()); //$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = ((java.lang.String)field_Description.getValue()); //$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = ((java.lang.String)field_UM.getValue()); //$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = ((java.lang.Double)field_Price.getValue()); //$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = ((java.lang.String)field_GoodQty.getValue()); //$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = ((java.sql.Date)field_DateNoDoc.getValue()); //$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = ((java.lang.String)field_Customer.getValue()); //$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = "Page " + ((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue()); //$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = new java.util.Date(); //$JR_EXPR_ID=21$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}
