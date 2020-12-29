package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class BoxDao {
	
	private static BoxDao instance;
	private BoxDao(){}
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public static BoxDao getInstance(){
		if(instance == null){
			instance = new BoxDao();
		}
		return instance;
	}
	
	public int boxOrderToday(String boxName){
		String sql = "select count(*) cnt from box_order where box_name = ?"
					+" and to_date(order_date) = to_date(sysdate)";
		
		List<Object> p = new ArrayList<>();
		p.add(boxName);
		Map<String, Object> result = jdbc.selectOne(sql,p);
		return Integer.parseInt(result.get("CNT").toString());
		
	}
	
	public Map<String, Object> boxMenu(String boxName){
		String sql = "select * from boxmenu where box_name = ?"
					+" and to_date(box_date) = to_date(sysdate)";
		List<Object> p = new ArrayList<>();
		p.add(boxName);
		return jdbc.selectOne(sql,p);
	}
	
	public boolean isOrderedToday(String boxName,String userId){
		String sql = "select count(*) cnt from box_order where box_name = ?"
					+" and user_id = ? and to_date(order_date) = to_date(sysdate)";
		List<Object> p = new ArrayList<>();
		p.add(boxName);
		p.add(userId);
		Map<String, Object> cnt = jdbc.selectOne(sql, p);
		return !cnt.get("CNT").toString().equals("0");
		
	}
	
	public int getPrice(String boxName){
		String sql = "select price from boxmenu where box_name = ?"
				+ " and box_date = to_date(sysdate)";
		List<Object> p = new ArrayList<>();
		p.add(boxName);
		Map<String, Object> price = jdbc.selectOne(sql, p);
		return Integer.parseInt(price.get("PRICE").toString());	
	}
	
	public int getMoney(String userId){
		String sql = "select * from users where user_id = ?";
		List<Object> p = new ArrayList<>();
		p.add(userId);
		Map<String, Object> user = jdbc.selectOne(sql, p);
		return Integer.parseInt(user.get("MONEY").toString());
	}
	
	public boolean payment(String userId, int price){
		String sql = "update users set money = money - ?"
					+" where  user_id = ?";
		List<Object> p = new ArrayList<>();
		p.add(price);
		p.add(userId);
		return jdbc.update(sql, p)==1;
	}
	
	public boolean cancelOrder(String boxName,String userId){
		String sql = "delete from box_order where box_name = ?"
				+" and user_id = ? and to_date(order_date) = to_date(sysdate)";
		List<Object> p = new ArrayList<>();
		p.add(boxName);
		p.add(userId);
		
		return jdbc.update(sql,p) == 1;
	}
	
	public boolean orderBox(String boxName, String userId){
		String sql = "insert into box_order(order_num,box_name, user_id, order_date, price)"
	       +" values((select nvl(max(order_num),0)+1 from box_order),?,?,sysdate,"
	       +"(select price from boxmenu where box_name = ? and box_date = to_date(sysdate)))";
		
		List<Object> p = new ArrayList<>();
		p.add(boxName);
		p.add(userId);
		p.add(boxName);
		
		return jdbc.update(sql,p)==1;
                                  
		
	}

}
