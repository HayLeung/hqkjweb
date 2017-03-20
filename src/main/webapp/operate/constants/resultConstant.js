define(function(require, exports) {


    /**
     * 状态码名称：status    以下是指向的值
     */


    /**
     * 状态码：10000   标识成功
     */
    exports.SUCCESS = "10000";   //标识成功
    /**
     * 状态码：10001  标识失败
     */
    exports.ERROR = "10001";   //标识失败
    /**
     * 状态码：10002  标识类型错误
     */
    exports.TYPE_ERROR = "10002";   //标识类型错误
    /**
     * 状态码：10003    标识超过限制大小
     */
    exports.SIZE_ERROR = "10003";   //标识超过限制大小
    /**
     *  状态码：10004   标识参数空值错误
     */
    exports.PARAM_NULL_ERROR = "10004";   //标识参数空值错误
    /**
     * 状态码：10005   标识返回空值错误
     */
    exports.RESULT_NULL_ERROR = "10005";   //标识返回空值错误
    /**
     *  状态码：10006   标识格式错误
     */
    exports.FORMAT_ERROR = "10006";   //标识格式错误
    /**
     * 状态码：10007   标识异常
     */
    exports.EXCEPTION_ERROR = "10007";   //标识异常
    /**
     * 状态码：10008   标识存在错误
     */
    exports.EXIST_ERROR = "10008";   //标识存在错误
    /**
     * 状态码：10009   标识不存在错误
     */
    exports.NO_EXIST_ERROR = "10009";   //标识不存在错误
    /**
     * 状态码：10010  标识状态错误
     */
    exports.STATUS_ERROR = "10010";   //标识状态错误
    /**
     * 状态码：10011   标识不匹配错误
     */
    exports.MATCH_ERROR = "10011";   //标识不匹配错误
    /**
     * 状态码：10012   标识操作类型不合法错误
     */
    exports.OPERATION_ERROR = "10012";   //标识操作类型不合法错误
    /**
     * 状态码：10013   标识密码错误
     */
    exports.PASSWORD_ERROR = "10013";   //标识密码错误
    /**
     * 状态码：10014  标识用户名或密码错误
     */
    exports.USERNAME$PASSWORD_ERROR = "10014";   //标识用户名或密码错误
    /**
     * 状态码：10015   标识未登录
     */
    exports.NOT_LOGIN_ERROR = "10015";   //标识未登录
    /**
     * 状态码：10016   标识内容超过限制
     */
    exports.CONTENT_SIZE_ERROR = "10016";    //标识内容超过限制
    /**
     * 状态码：10017   标识加密异常
     */
    exports.ENCRYPTION_EXCEPTION = "10017";   //加密异常
    /**
     * 状态码：10018   标识不可删除
     */
    exports.NOT_DELETE ="10018";   //标识不可删除
    /**
     * 状态码：10019    标识无权限
     */
    exports.NOT_POWER ="10019";   //标识无权限
    /**
     * 状态码：10020    标识无文件
     */
    exports.NOT_FILE ="10020";   //标识无文件

    /**
     * 状态码：10020    标识无文件
     */
    exports.LOGIN_CODE ="10021";   //标识验证码错误

    /**
     * 解密约定
     */
    exports.SYSTEM_PWD_KEY = "ADMIN@YCXC20170106DESKEY";    //前后端解密的Key



});