package com.hqkj.example.entity;

public class RoleFunc {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_func.role_func_id
     *
     * @mbggenerated
     */
    private Integer roleFuncId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_func.role_id
     *
     * @mbggenerated
     */
    private Integer roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_func.func_id
     *
     * @mbggenerated
     */
    private String funcId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_func.role_func_id
     *
     * @return the value of role_func.role_func_id
     *
     * @mbggenerated
     */
    public Integer getRoleFuncId() {
        return roleFuncId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_func.role_func_id
     *
     * @param roleFuncId the value for role_func.role_func_id
     *
     * @mbggenerated
     */
    public void setRoleFuncId(Integer roleFuncId) {
        this.roleFuncId = roleFuncId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_func.role_id
     *
     * @return the value of role_func.role_id
     *
     * @mbggenerated
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_func.role_id
     *
     * @param roleId the value for role_func.role_id
     *
     * @mbggenerated
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_func.func_id
     *
     * @return the value of role_func.func_id
     *
     * @mbggenerated
     */
    public String getFuncId() {
        return funcId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_func.func_id
     *
     * @param funcId the value for role_func.func_id
     *
     * @mbggenerated
     */
    public void setFuncId(String funcId) {
        this.funcId = funcId == null ? null : funcId.trim();
    }
}