package com.javapai.framework.common.dto;

/**
 * 
 * 基础请求参数对象。<br>
 * 
 * <strong>BaseParam与BaseDTO区别在于：一个使用继承，一个使用组合;定义此类的原因仅为方便使用者多向选择。</strong>
 * 
 * <br>
 * 1、json报文结构：<br>
 * 前提：结构意在定义http-body部分的参数结构。
 * { "args-head":{ }, "args-body":{ }, "args-page":{ } }
 * 
 * 2、json结构说明：<br>
 * 
 * 3、json请求样例：<br>
 * 
 * 
 * 格式说明：请求格式分请求头和请求参数两部分，<br>
 * json请求头负责验证访问权限，请求参数负责验证业务数据，这样分离后的最大好处就是稳定了json的数据格式。 <br>
 * 
 * 请求标准json格式(V1)：<br>
 * { "head": <br>
 * { <br>
 * "appId": "234566", <br>
 * "version": "v1.0", <br>
 * "comeFrom": "1", <br>
 * "sign": "6cd7a0cec3ba9bbab2f95a4570aa54a5", <br>
 * "token": { "userId": 10006, <br>
 * "tokenId": "18516632554" }, }, <br>
 * "args": { "bill": { "billType":1, <br>
 * "billNo":40200051, "issueDate":"2015-08-21",<br>
 * "dueDate":"2016-08-21", "payerCompany":"浙江联谊建筑工程有限公司",<br>
 * "payerBank":"萧山农商银行城厢支行", "payeeCompany":"杭州前程混粘土有限公司",<br>
 * "payeeBank":"萧山农商银行城厢支行" } } } <br>
 * <br>
 * 在请求头不变的情况下，对Args参数： 版本V1(适合增加与修改删除)： "args": { "key1":value1, "key2":value2,
 * "key3":"value3", "dueDate":"2016-08-21", "payerCompany":"浙江联谊建筑工程有限公司",
 * "payerBank":"萧山农商银行城厢支行", "payeeCompany":"杭州前程混粘土有限公司",
 * "payeeBank":"萧山农商银行城厢支行" } <br>
 * 版本v2(适合查询分页与排序)： "args": { "key1":value1, "key2":value2, "key3":"value3",
 * "dueDate":"2016-08-21", "payerCompany":"浙江联谊建筑工程有限公司",
 * "payerBank":"萧山农商银行城厢支行", "payeeCompany":"杭州前程混粘土有限公司",
 * "payeeBank":"萧山农商银行城厢支行" } <br>
 * <p>
 * 原名：RstParam
 * 
 * @author liu.xiang
 * 
 *         过期原因： <br>
 *         1:RstHead对象一般只用来存放token令牌，这时RstHead不需要和RstBody一起被打包成一个BaseParam对象被传递到后端(RstHead内容可直接放在http-header中)。<br>
 *         2：也考虑过将RstHead类作为BaseParam的父类，但没有改变RstHead的根本意义(缺陷)，只是在Class设计上的一种权衡（组合
 *         or 承继）[dto参数尽量 平行继承，vo返回尽量 组合返回]。<br>
 * @deprecated 代替对象：{@link com.javapai.framework.common.dto.BaseRstDTO}
 *  * Request Header.<br>
 * JSON形式统一请求报文.<br>
 * 
 * 目的：用以取代{@link #com.javapai.framework.action.RstParam}
 * 
 * 改进点：
 * <br>
 * 1、将RstParam.RstHead对象的每个属性给出明确的定义且每个属性与RstBody平行所有属性外置平行化;
 * 2、原来RstHead请求头参数过多且大多为业务参数，为统一只将token参数放置于http-header中，其它参数放于http-body中.
 * <br>
 * 
 * RequestHead类是在BaseDTO基础上扩展出业务上所需要的共公请求参数。<br>
 * 
 */
@Deprecated
public abstract class BaseParam<T> {
	/**
	 * 请求参数头.<br>
	 */
	private RstHead head = null;
	/**
	 * 请求参数体.<br>
	 */
	private T body = null;
	// private RstBody body = null;

	/**
	 * 请求参数头.<br>
	 * 
	 * @author pooja
	 *
	 */
	class RstHead {
		private String token;
		private String appId;
	};

	/**
	 * 请求参数体.<br>
	 * 
	 * @author pooja
	 *
	 */
	class RstBody<T> {
		private T rstDate;
	}

}
