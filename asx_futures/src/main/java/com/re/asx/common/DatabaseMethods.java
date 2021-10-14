package com.re.asx.common;
import java.util.List;
import com.re.asx.config.Settings;
public class DatabaseMethods {
	
	public static void writeIntoDB(List<FuturesData> fd){
		FuturesData object=new FuturesData();
		for(int i=0;i<fd.size();i++)
		{
		object=fd.get(i);
		String insertString="INSERT INTO re_asxfutures (read_date,market ,region ,contract_type ,contract_period ,bid_size "
				+ ",bid,ask ,ask_size ,high ,low ,last ,delta "
				+ ",vol ,openint ,openint_delta,settle )   VALUES ('"+object.read_date+"','"+object.market+"','"+object.region+"','"+object.contract_type+"','"
				+object.contract_period+"',"+object.bid_size+","+object.bid+","+object.ask+","+object.ask_size+","+object.high+","+object.low+","+object.last+","
				+object.delta+","+object.vol+","+object.openint+","+object.openint_delta+","+object.settle+");";
		
		Settings.Logs.Write(insertString);
		/*System.out.print(object.read_date);System.out.print("\t");
		System.out.print(object.market);System.out.print("\t");
		System.out.print(object.region);System.out.print("\t");
		System.out.print(object.contract_type);System.out.print("\t");
		System.out.print(object.contract_period);System.out.print("\t");
		System.out.print(object.bid_size);System.out.print("\t");
		System.out.print(object.bid);System.out.print("\t");
		System.out.print(object.ask);System.out.print("\t");
		System.out.print(object.ask_size);System.out.print("\t");
		System.out.print(object.high);System.out.print("\t");
		System.out.print(object.low);System.out.print("\t");
		System.out.print(object.last);System.out.print("\t");
		System.out.print(object.delta);System.out.print("\t");
		System.out.print(object.vol);System.out.print("\t");
		System.out.print(object.openint);System.out.print("\t");
		System.out.print(object.openint_delta);System.out.print("\t");
		System.out.print(object.settle);System.out.print("\n");*/
	
	}
		
	}

}
