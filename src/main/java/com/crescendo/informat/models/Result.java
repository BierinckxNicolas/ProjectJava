package com.crescendo.informat.models;

/** Result POJO
 * @author Groep 5
 */
public class Result {

    private float score;
    private float maxScore;
    private String pointcodes;

    public Result() {
    }

    public Result(float score, float maxScore, String pointcodes) {
        this.score = score;
        this.maxScore = maxScore;
        this.pointcodes = pointcodes;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(float maxScore) {
        this.maxScore = maxScore;
    }

    public String getPointcodes() {
        return pointcodes;
    }

    public void setPointcodes(String pointcodes) {
        this.pointcodes = pointcodes;
    }

    @Override
    public String toString() {
        return "Result{" +
                "score=" + score +
                ", maxScore=" + maxScore +
                ", pointcodes='" + pointcodes + '\'' +
                '}';
    }
}
