package com.javapai.framework.enums;

/**
 * 错误码枚举类(非因特殊原因，请勿随意修改).<br>
 * 
 * 错误码定义规范：8位长度=2位系统码+3位预留位+3位错误码.<br>
 * <br>
 * <br>
 * 说明：错误码用来定义不同接口间调用时的状态表达，为防错误码[code]出现爆炸且难以维护,不同系统间的异常码定义交由不同接口提供方根据<strong>错误码命名规范</strong>和当时业务场景自行决定.
 * 
 * @author liuxiang
 *
 */
public enum ErrorCode implements Enums<String,String> {
	/* 常规类-错误码 */
	EXCEPTION_UNDEFINE("FFFFFFFF", "预留用户自定义异常!"),//预留用户自定义系统异常.
	EXCEPTION_RPC("40000101", "系统RPC服务异常!"),//跨系统RPC间调用异常.
	EXCEPTION_HTTP("40000102", "系统HTTP服务异常!"),//跨系统HTTP间调用异常.
    EXCEPTION_CREATE("40000991", "系统创建服务异常!"),//本系统内CRUD操作异常.
    EXCEPTION_UPDATE("40000992", "系统更新服务异常!"),//本系统内CRUD操作异常.
    EXCEPTION_DELETE("40000993", "系统删除服务异常!"),//本系统内CRUD操作异常.
	EXCEPTION_SELECT("40000994", "系统查询服务异常!"),//本系统内CRUD操作异常.
	
	/* 参数类-错误码 */
	PARAMS_EMPTY("410001001","必要参数不允许为空!"),
    PARAMS_ILLEGE("410001002","参数格式不合法"),
    PARAMS_VERISON("410001003","应用版本号参数缺失!"),
    PARAMS_PRODUCT("410001004","产品线参数缺失!"),
    PARAMS_CHANNEL("410001005","渠道参数缺失!"),
    PARAMS_REQUESTIP("410001006","请求源IP缺失!"),
    PARAMS_USERNAME("410001017","登录账号参数缺失!"),
    PARAMS_USERID("410001007","用户标识参数缺失!"),
    PARAMS_CAPTCHA("41000108","验证码参数缺失!"),
    PARAMS_PHONE("41000109","手机号参数缺失!"),
    PARAMS_EDU("410001004","学历参数为空!"),
    PARAMS_SCHOOL("410001004","无效学校信息"),
    PARAMS_RUXUEDATA("410001204","入学时间参数为空!"),
    PARAMS_ADDRESS("410001205","地址参数不能为空"),
    PARAMS_LASTNAME("41000100","姓名参数不能为空"),
    PARAMS_APPID("41000101","应用标识参数不允许为空!"),
    PARAMS_ID("41000101","数据标识参数不允许为空!"),
    
	/* 业务类-错误码  */
    ERROR_REGISTER("", "用户注册失败!"),
    ERROR_LOGIN("40000221", "登录错误，请检查用户名或密码!"),
    ERROR_LOGOUT("40000221", "用户退出失败!"),
    ERROR_UPLOAD("40000221", "上传操作失败!"),
    
	/* 无效类-错误码 */
    INVALID_APPID("40000110", "无效的App应用标识!"), 
    INVALID_TOKEN("40000111", "无效的token登录授权码!"), 
    INVALID_ID("40000112", "无效的id编码!"), 
    INVALID_CODE("40000112", "无效的code编码!"), 
	/**
	 * 
	 * @deprecated 使用{@link ErrorCode#INVALID_TOKEN}代替。<br>
	 */
	ERROR_TOKEN_EXPIRE("4000999", "登录授权码已过期!"),
	
	ERROR_EDU_INVALID("40000235","学籍认证失败"),
	ERROR_USER_AUTH_BANKCARD("40000241", "银行卡号与实名认证身份不符!"),
	ERROR_USER_AUTH_LOCKED("40000270", "因提交次数过多，认证功能已被锁定!"),
	ERROR_USER_CAPTCHA_INVALID("40000280", "无效的验证码!"),
	ERROR_USER_IDCARD_INVALID("","身份证号无效"),
	ERROR_USER_IDCARD_18OVER("","身份证号验证失败,未满18岁"),
	ERROR_USER_AUTH_IDCARD_INVALID("","身份证号验证失败,姓名不一致"),
	
	/* 唯一核验类-错误码 */
    EXIST_AUTH_EDU("40000371", "你的学籍已经核查通过，请勿再提交!"),
    EXIST_AUTH_REALNAME("40000372", "无效的验证码!"),
    EXIST_CODE("40000377", "该编码已存在!"), 
	EXIST_USER("40000377", "该用户已存在!"), 
	EXIST_NAME("40000377", "该名称已存在!"), 
	EXIST_PHONE("40000378", "该手机号码已被注册!"),
	
	PASSWORD_LENGH("", "密码长度要求不达标!"), // 具体多长取决于业务API进一步说明.
	PASSWORD_PATTERN("", "密码格式为要求不达标!"), // 具体格式取决于API进一步说明.
	
//    ,INFO_ERROR(ErrorCode.ERROR_CODE+101, "信息填写不完整!")
//    ,FIRST_SHAKE_ERROR(ErrorCode.ERROR_CODE+103,"第一次握手失败")
//    ,SECOND_SHAKE_ERROR(ErrorCode.ERROR_CODE+104,"第二次握手失败")
//    ,ENCRYPTION_ERROR(ErrorCode.ERROR_CODE+105,"加密")
//    ,UUID_ERROR(ErrorCode.ERROR_CODE+106,"UUID格式错误")
//    ,SIGN_VERIFY_FAILED(ErrorCode.ERROR_CODE+107,"签名失败")
//	,PASSWORD_ERROR(ErrorCode.ERROR_CODE+201, "用户名或密码错误!")
//    ,REALNAME_VALID_OUTTIMES(ErrorCode.ERROR_CODE+210,"实名认证次数过多")
//    ,REALNAME_PROTECTED(ErrorCode.ERROR_CODE+211,"实名认证通过,用户资料不可修改")
//    ,REALNAME_VALID_API_FAIL(ErrorCode.ERROR_CODE+213,"身份证号验证请求失败")
//    ,REALNAME_VALID_FAIL(ErrorCode.ERROR_CODE+214,"身份证号验证失败")
//    ,REALNAME_NOT_FOUND(ErrorCode.ERROR_CODE+215,"无法找到实名资料")
//    ,REALNAME_NOT_VALID(ErrorCode.ERROR_CODE+218,"身份证未通过验证,请先完成实名认证。")
//    ,EDU_VALID_INVALID_DISTRICT(ErrorCode.ERROR_CODE+231,"无效区县信息")
//    ,EDU_INFO_PROTECTED(ErrorCode.ERROR_CODE+232,"学籍认证通过,学籍资料不可修改")
//    ,EDU_VALID_API_FAIL(ErrorCode.ERROR_CODE+233,"学籍认证请求失败")
//    ,EDU_VALID_OUTTIMES(ErrorCode.ERROR_CODE+234,"学籍认证次数溢出过多")
//    ,EDU_NOT_FOUND(ErrorCode.ERROR_CODE+215,"无法找到学籍资料")
//    ,CONTACT_SAVE_FAIL(ErrorCode.ERROR_CODE+214,"联系人信息保存失败!")
//    ,ZHIMA_VALID_API_FAIL(ErrorCode.ERROR_CODE+301,"芝麻认证接口调用失败")
//    ,DEBITCARD_NOT_FOUND(ErrorCode.ERROR_CODE+402,"未取得用户借记卡信息!")
//    ,DEBITCARD_TOO_MANY(ErrorCode.ERROR_CODE+402,"该卡号存在多条绑卡信息！")
//    ,BORROW_FEE_NOT_FOUND(ErrorCode.ERROR_CODE+404,"未取得产品对应的借款利费率信息!")
//    ,BORROW_PERIOD_MAP_NOT_FOUND(ErrorCode.ERROR_CODE+405,"未取得借款周期信息!")
//    ,IS_QL_BORROWING_YES(ErrorCode.ERROR_CODE+407,"用户已存在借款中的订单!")
//    ,IS_UZ_BORROWING_YES(ErrorCode.ERROR_CODE+411,"用户已存在借款中的订单!")
//    ,NOT_ENOUGH_LIMIT(ErrorCode.ERROR_CODE+408,"没有足够的可用额度!")
//    ,BIND_COUPON_FAIL(ErrorCode.ERROR_CODE+409,"绑定U券失败!")
//    ,BIND_COUPON_EXPIRED(ErrorCode.ERROR_CODE+410,"您的u券已过期")
//    ,VALIDCODE_MORE(ErrorCode.ERROR_CODE+108,"验证码发送过于频繁")
//    ,FAIL_GET_SINGLERATE(ErrorCode.ERROR_CODE+109,"获取费率失败")
//    ,
//    ,PARAMS_PASSWORD(ErrorCode.ERROR_CODE+2,"密码参数缺失!")
//    ,PARAMS_AUTH_CODE(ErrorCode.ERROR_CODE+2,"授权码参数缺失!")
//    ,PARAMS_ORDER_ID(ErrorCode.ERROR_CODE+8,"订单号参数缺失!")
//    ,PARAMS_BANKCARD(ErrorCode.ERROR_CODE+406,"银行卡不能为空!")
//    ,PARAMS_BANKNAME(ErrorCode.ERROR_CODE+406,"银行名称不能为空!")
//    ,PARAMS_NEWPWD(ErrorCode.ERROR_CODE+2,"新密码不能为空")
//    ,PARAMS_NEWPWDAGAIN(ErrorCode.ERROR_CODE+2,"再次输入新密码不能为空")
//    ,PARAMS_OLDPWD(ErrorCode.ERROR_CODE+2,"老密码不能为空")
//    ,PARAMS_VALIDCODETYPE(ErrorCode.ERROR_CODE+2,"验证码类型缺失")
//    ,PARAMS_WEBKEY(ErrorCode.ERROR_CODE+2,"网页key值缺失")
//    ,PARAMS_CARDID(ErrorCode.ERROR_CODE+2,"身份证不能为空")
//    ,PARAMS_SCHOOL(ErrorCode.ERROR_CODE+2,"学校不能为空")
//    ,PARAMS_PROVINCE(ErrorCode.ERROR_CODE+2,"省份不能为空")
//    ,PARAMS_CITY(ErrorCode.ERROR_CODE+2,"城市不能为空")
//    ,PARAMS_DISTRICT(ErrorCode.ERROR_CODE+2,"区县不能为空")
//    ,PARAMS_BUILDING(ErrorCode.ERROR_CODE+2,"楼号不能为空")
//    ,PARAMS_ROOM(ErrorCode.ERROR_CODE+2,"房号不能为空")
//    ,PARAMS_ADMYEAR(ErrorCode.ERROR_CODE+2,"入学年份不能为空")
//    ,PARAMS_RUXUEDATE(ErrorCode.ERROR_CODE+108,"入学时间参数缺失")
//    ,PARAMS_PERIOD(ErrorCode.ERROR_CODE+108,"还款期数参数缺失")
//    ,PARAMS_REPAYUID(ErrorCode.ERROR_CODE+108,"还款渠道id参数缺失")
//    ,PARAMS_REPAYTYPE(ErrorCode.ERROR_CODE+108,"还款类型参数缺失")
//  ,PARAMS_BORROW_TYPE(ErrorCode.ERROR_CODE+10,"借款类型参数缺失!")
    ;

	
    private String code;
    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return message;
	}
}
