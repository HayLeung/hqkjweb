package com.hqkj.example.entity;

public class UserVehicle {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column uservehicle.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column uservehicle.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column uservehicle.vehicle_id
     *
     * @mbggenerated
     */
    private Integer vehicleId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column uservehicle.id
     *
     * @return the value of uservehicle.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column uservehicle.id
     *
     * @param id the value for uservehicle.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column uservehicle.user_id
     *
     * @return the value of uservehicle.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column uservehicle.user_id
     *
     * @param userId the value for uservehicle.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column uservehicle.vehicle_id
     *
     * @return the value of uservehicle.vehicle_id
     *
     * @mbggenerated
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column uservehicle.vehicle_id
     *
     * @param vehicleId the value for uservehicle.vehicle_id
     *
     * @mbggenerated
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
}