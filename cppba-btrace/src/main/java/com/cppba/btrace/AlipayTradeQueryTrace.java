package com.cppba.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.Return;
import com.sun.btrace.annotations.TargetInstance;
import com.sun.btrace.annotations.TargetMethodOrField;

/**
 * @author winfed
 * @create 2017-11-02 10:34
 */
@BTrace
public class AlipayTradeQueryTrace {

    /**
     * 进入方法的时候，捕获入参
     *
     * @param outTradeNo 订单编号
     */
    @OnMethod(
            clazz = "com.cppba.alipay.controller.AlipayTradeQueryController",
            method = "doTradeQuery",
            location = @Location(value = Kind.ENTRY)
    )
    public static void doTradeQueryTraceEntry(String outTradeNo) {

        BTraceUtils.println("=== 进入方法时入参 ↓ ===");
        BTraceUtils.println("outTradeNo = [" + outTradeNo + "]");
        BTraceUtils.println();
    }

    /**
     * 返回的时候,捕获入参编号和返回值
     *
     * @param outTradeNo 订单编号
     * @param result     返回值
     */
    @OnMethod(
            clazz = "com.cppba.alipay.controller.AlipayTradeQueryController",
            method = "doTradeQuery",
            location = @Location(value = Kind.RETURN)
    )
    public static void doTradeQueryTraceReturn(
            String outTradeNo, @Return Object result, @ProbeClassName String probeClassName,
            @ProbeMethodName String probeMethodName, @Duration long duration) {
        String alipayResponse = "com.cppba.alipay.base.response.AlipayResponse";
        String alipayTradeQueryResponse = "com.cppba.alipay.response.AlipayTradeQueryResponse";

        BTraceUtils.println("=== 执行详情 ↓ ===");
        BTraceUtils.println("调用类:[" + probeClassName + "]," +
                "调用方法:[" + probeMethodName + "]," +
                "执行时长:[" + duration / 1000000 + "] ms");
        BTraceUtils.println();

        BTraceUtils.println("=== 方法执行完入参 ↓ ===");
        BTraceUtils.println("outTradeNo = [" + outTradeNo + "]");
        BTraceUtils.println();

        BTraceUtils.println("=== 返回值 ↓ ===");
        BTraceUtils.println("code = [" + BTraceUtils.get(BTraceUtils.field(alipayResponse, "code"), result) + "]");
        BTraceUtils.println("msg = [" + BTraceUtils.get(BTraceUtils.field(alipayResponse, "msg"), result) + "]");
        BTraceUtils.println("subCode = [" + BTraceUtils.get(BTraceUtils.field(alipayResponse, "subCode"), result) + "]");
        BTraceUtils.println("subMsg = [" + BTraceUtils.get(BTraceUtils.field(alipayResponse, "subMsg"), result) + "]");
        BTraceUtils.println("tradeNo = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "tradeNo"), result) + "]");
        BTraceUtils.println("outTradeNo = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "outTradeNo"), result) + "]");
        BTraceUtils.println("buyerLogonId = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "buyerLogonId"), result) + "]");
        BTraceUtils.println("tradeStatus = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "tradeStatus"), result) + "]");
        BTraceUtils.println("totalAmount = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "totalAmount"), result) + "]");
        BTraceUtils.println("receiptAmount = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "receiptAmount"), result) + "]");
        BTraceUtils.println("buyerPayAmount = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "buyerPayAmount"), result) + "]");
        BTraceUtils.println("poinAmount = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "poinAmount"), result) + "]");
        BTraceUtils.println("invoiceAmount = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "invoiceAmount"), result) + "]");
        BTraceUtils.println("sendPayDate = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "sendPayDate"), result) + "]");
        BTraceUtils.println("storeId = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "storeId"), result) + "]");
        BTraceUtils.println("terminalId = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "terminalId"), result) + "]");
        // BTraceUtils.println("fundBillList = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "fundBillList"), result) + "]");
        BTraceUtils.println("storeName = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "storeName"), result) + "]");
        BTraceUtils.println("buyerUserId = [" + BTraceUtils.get(BTraceUtils.field(alipayTradeQueryResponse, "buyerUserId"), result) + "]");
        BTraceUtils.println();
    }

    /**
     * 捕获被调用时
     *
     * @param probeClassName      发起调用类
     * @param probeMethodName     发起调用方法
     * @param targetInstance      被调用类
     * @param targetMethodOrField 被调用方法
     */
    @OnMethod(
            clazz = "/com\\.cppba\\..*/",
            method = "/.*/",
            location = @Location(
                    value = Kind.CALL,
                    clazz = "/com\\.cppba\\..*/",
                    method = "createResponse")
    )
    public static void doTradeQueryTraceCall(
            @ProbeClassName String probeClassName,
            @ProbeMethodName String probeMethodName,
            @TargetInstance Object targetInstance,
            @TargetMethodOrField String targetMethodOrField) {
        BTraceUtils.println("=== 执行详情 ↓ ===");
        BTraceUtils.println("调用类:[" + probeClassName + "]," +
                "调用方法:[" + probeMethodName + "]," +
                "执行类:[" + (targetInstance == null ? "null" : BTraceUtils.str(BTraceUtils.classOf(targetInstance))) + "]," +
                "执行方法:[" + targetMethodOrField + "]");
        BTraceUtils.println();
    }
}
