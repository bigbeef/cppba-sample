package com.cppba.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 人脸对比
 *
 * @author winfed
 * @created 2018/3/12 16:13
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class FaceMatchResponse {

    /**
     * 请求唯一标识码，随机数
     * 73473737
     */
    @JsonProperty("log_id")
    private Long logId;

    /**
     * 返回结果数目，即：result数组中元素个数
     * 1
     */
    @JsonProperty("result_num")
    private Integer resultNum;

    /**
     * 结果数据，index和请求图片index对应。数组元素为每张图片的匹配得分数组，top n。得分范围[0,100.0]
     */
    @JsonProperty("result")
    private List<Result> resultList;

    @Data
    public static class Result {

        /**
         * 比对图片1的index
         * 0
         */
        @JsonProperty("index_i")
        private Integer indexI;

        /**
         * 比对图片2的index
         * 1
         */
        @JsonProperty("index_j")
        private Integer indexJ;

        /**
         * 比对得分，推荐80分作为阈值，80分以上可以判断为同一人，此分值对应万分之一误识率
         * 92.966453552246
         */
        @JsonProperty("score")
        private Double score;
    }

    /**
     * 获取本次人像对比的匹配度，默认取result[0].score
     *
     * @return
     */
    public Double getScore() {
        if (resultList != null && resultList.size() > 0) {
            Result result = resultList.get(0);
            return result.getScore();
        }
        return 0D;
    }

}
