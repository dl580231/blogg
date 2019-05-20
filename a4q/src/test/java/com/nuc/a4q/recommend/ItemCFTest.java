package com.nuc.a4q.recommend;

import java.util.List;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.nuc.a4q.BaseTest;

public class ItemCFTest extends BaseTest {
	@Autowired
	private  ComboPooledDataSource dataSource;
	 final static int NEIGHBORHOOD_NUM = 2;
	 final static int RECOMMENDER_NUM = 10;

	 @Test
	    public void demo() throws Exception
	    {	
	    	/*//操作文件
	        URL url=ContentBased.class.getClassLoader().getResource("data.csv");
	        DataModel model = new FileDataModel(new File(url.getFile()));*/
	    	//操作数据库
	    	/*MysqlDataSource dataSource = new MysqlDataSource();
	    	dataSource.setUser("root");
	    	dataSource.setPassword("toor");
	    	dataSource.setURL("jdbc:mysql://127.0.0.1/a4q?useUnicode=true&characterEncoding=utf8");*/
	    	MySQLJDBCDataModel model = new MySQLJDBCDataModel(dataSource,"tb_post_history","user_id","post_id","preference", null);
	    	
	    	
	        ItemSimilarity item = new EuclideanDistanceSimilarity(model);

	        Recommender r = new GenericItemBasedRecommender(model,item);

	        LongPrimitiveIterator iter = model.getUserIDs();

	        while(iter.hasNext())
	        {
	            long uid = iter.nextLong();
	            List<RecommendedItem> list = r.recommend(uid,RECOMMENDER_NUM);  //获取推荐结果
	            System.out.printf("用户:%s",uid);
	            System.out.print("|| 推荐商品：");
	            //遍历推荐结果
	            for(RecommendedItem ritem : list)
	            {
	                System.out.printf(ritem.getItemID()+" ");
	            }
	            System.out.println();
	        }
	        List<RecommendedItem> list = r.recommend(27,RECOMMENDER_NUM);  //获取推荐结果
	    }
}