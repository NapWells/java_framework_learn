package org.yyh.model;

import java.util.List;

/**
 * @author: lhy
 * @description:
 * @date: 2019-03-06 14:18
 **/
public class ResultModel {
    private Long totalHits;
    private Float maxScore;
    private List<ItemModel> itemModels;

    public Long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Long totalHits) {
        this.totalHits = totalHits;
    }

    public Float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Float maxScore) {
        this.maxScore = maxScore;
    }

    public List<ItemModel> getItemModels() {
        return itemModels;
    }

    public void setItemModels(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }
}
