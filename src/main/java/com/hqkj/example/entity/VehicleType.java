package com.hqkj.example.entity;

public class VehicleType {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vehicle_type.vehicle_type_id
     *
     * @mbggenerated
     */
    private Integer vehicleTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vehicle_type.vehicle_type
     *
     * @mbggenerated
     */
    private String vehicleType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vehicle_type.vehicle_type_id
     *
     * @return the value of vehicle_type.vehicle_type_id
     *
     * @mbggenerated
     */
    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vehicle_type.vehicle_type_id
     *
     * @param vehicleTypeId the value for vehicle_type.vehicle_type_id
     *
     * @mbggenerated
     */
    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vehicle_type.vehicle_type
     *
     * @return the value of vehicle_type.vehicle_type
     *
     * @mbggenerated
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vehicle_type.vehicle_type
     *
     * @param vehicleType the value for vehicle_type.vehicle_type
     *
     * @mbggenerated
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType == null ? null : vehicleType.trim();
    }
}