<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付</title>

<script type="text/javascript">
	//json 数据
	var x_json=${json};
	function onBridgeReady() {
		WeixinJSBridge.invoke('getBrandWCPayRequest', x_json, function(res) {
			if (res.err_msg == 'get_brand_wcpay_request:ok') {
				//支付成功，可以做跳转到支付成功的提示页面
				//alert("支付成功");
				//todo:跳转到支付成功展示页面
				window.location.href="payresult/"; 
			} else {
				//支付失败
				//alert(res.err_msg);
				window.location.href="errorpay/"; 
			}
		});
	}

	if (typeof WeixinJSBridge == "undefined") {
		if (document.addEventListener) {
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
					false);
		} else if (document.attachEvent) {
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	} else {
		onBridgeReady();
	}
</script>
</head>
<body>

</body>
</html>
