package org.settle.htgl.contractFen.cmd;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.demo.dao.Ttest;
import org.json.JSONObject;
import org.loushang.next.dao.DaoFactory;
import org.loushang.next.dao.EntityDao;
import org.loushang.next.data.DataSet;
import org.loushang.next.data.ParameterSet;
import org.loushang.next.data.Record;
import org.loushang.next.web.cmd.BaseQueryCommand;
import org.settle.base.XmlUtil;
import org.settle.htgl.contractFen.dao.TfContractfenInfo;
import org.settle.htgl.contractFen.dao.TfContractfenInfoDao;
import org.settle.htgl.contractFen.dao.WwhtzfmxInfo;

import com.google.gson.Gson;


/**
 * @title:TfContractfenInfoQueryCommand
 * @description:
 * @author:
 * @since:2015-07-22
 * @version:1.0
*/
public class TfContractfenInfoQueryCommand extends BaseQueryCommand{

	public DataSet execute() {
		ParameterSet pset = getParameterSet();
		pset.setSortField("CONTRACT_YEAR DESC,to_number(CONTRACT_ORDER)");
		pset.setSortDir("ASC");
		EntityDao<TfContractfenInfo> dao = (EntityDao<TfContractfenInfo>) DaoFactory
				.getDao("org.settle.htgl.contractFen.dao.TfContractfenInfoDao");
		DataSet ds = dao.query(pset);
		for(int i = 0;i<ds.getCount();i++){
			Record red = ds.getRecord(i);
			String isDependence=(String) red.get("isDependence");
			String dependenceName="";
			if(("Y").equals(isDependence)){
				dependenceName="是";
			}else if(("N").equals(isDependence)){
				dependenceName="否";
			}
			red.set("dependenceName", dependenceName);
		}
		return ds;
	}
	
	//委外合同支付明细表
	public DataSet wwhtzfmx(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String time1=df.format(new Date());
		ParameterSet pset = getParameterSet();
		 TfContractfenInfoDao dao = (TfContractfenInfoDao) DaoFactory
		.getDao("org.settle.htgl.contractFen.dao.TfContractfenInfoDao");
		DataSet ds = dao.wwhtzfmx(pset);
		Record xx=ds.getRecord(0);
		WwhtzfmxInfo sss=(WwhtzfmxInfo)xx.toBean(WwhtzfmxInfo.class);
		Gson gson = new Gson();
		String str = gson.toJson(sss);
		System.out.println("测试json"+str);
		//JSONObject json2 = JSONObject.fromObject(str);
		//System.out.println("测试json22"+json2);
		XmlUtil changetoxml=new XmlUtil();
		//WwhtzfmxInfo obj=new WwhtzfmxInfo();
		String xmlString=changetoxml.toXml(sss);
		System.out.println("xml转换："+xmlString);
		//String xml2 = xmlSerializer.write(str);
		//String time2=df.format(new Date());
		//System.out.println("时间1"+time1+"时间2"+time2);
//		ds.setCustomData("ds1", ds1);
		return ds;
	}
	
	
	//委外合同签订情况表
	public DataSet wwhtqdinfo(){
		ParameterSet pset = getParameterSet();
		 TfContractfenInfoDao dao = (TfContractfenInfoDao) DaoFactory
		.getDao("org.settle.htgl.contractFen.dao.TfContractfenInfoDao");
		DataSet ds = dao.wwhtqdinfo(pset);
//		ds.setCustomData("ds1", ds1);
		return ds;
	}
	
	//委外项目汇总表
	public DataSet wwxmhzb(){
		ParameterSet pset = getParameterSet();
		 TfContractfenInfoDao dao = (TfContractfenInfoDao) DaoFactory
		.getDao("org.settle.htgl.contractFen.dao.TfContractfenInfoDao");
		DataSet ds = dao.wwxmhzb(pset);
		return ds;
	}
}
