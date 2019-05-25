package com.nuc.a4q.recommend;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class ItemCF {
	@Autowired
	private ComboPooledDataSource dataSource;
//	private final static int NEIGHBORHOOD_NUM = 2;
	private final static int RECOMMENDER_NUM = 10;
	
	/**
	 * 根据传来的userId进行推荐
	 * @param userId
	 * @return
	 * @throws TasteException
	 */
	public List<Integer> getRecommengBasedItemCF(Integer userId,String table,String key) throws TasteException
	{	
		MySQLJDBCDataModel model = new MySQLJDBCDataModel(dataSource,table,"user_id",key,"preference", null);
        ItemSimilarity item = new EuclideanDistanceSimilarity(model);//基于欧几里得距离算法计算相似度
        Recommender r = new GenericItemBasedRecommender(model,item);
        List<RecommendedItem> list = r.recommend(userId,RECOMMENDER_NUM);//获取推荐结果
        ArrayList<Integer> recommendList = new ArrayList<Integer>();
        //遍历推荐结果
        for(RecommendedItem ritem : list)
        {
        	recommendList.add((int) ritem.getItemID());
        }
        return recommendList;
	}
    
}