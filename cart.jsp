<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset = "utf8">
        <link rel="stylesheet" href="./css/mimosa.css">
		<title>カート</title>
	</head>
	<body>
		<s:include value="header.jsp"/>
		<div id="contents">
			<h1 class="title">カート画面</h1>
			<s:if test="#session.checkListErrorMessageList != null">
				<div class="error-message">
					<s:iterator value="#session.checkListErrorMessageList"><s:property/></s:iterator>
				</div>
			</s:if>
			<s:if test="#session.cartInfoDtoList.size()>0">
				<p class="message">カートには以下の商品が入っています</p>
				<s:form id="form" action="SettlementConfirmAction">
					<s:iterator value="#session.cartInfoDtoList">
	                   	<div class="cart-contents">
	                       	<div class="cart-left">
	                           	<div class="checkbox"><s:checkbox name="checkList" value="checked" fieldValue="%{productId}"/></div>
	                           	<s:hidden name="productId" value="%{productId}"/>
	                           	<img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' width="300px" height="300px" />
	                       	</div>
	                       	<div class="cart-right">
	                           	<div class="product-name-kana"><s:property value="productNameKana"/></div>
	                           	<div class="product-name"><s:label value="商品名:"/><s:property value="productName"/></div>
	                           	<div class="price"><s:label value="価格:"/><s:property value="price"/>円</div>
	                           	<s:label value="(購入数:"/><s:property value="productCount"/>点)
	                           	<div class="company">
	                           		<s:label value="発売会社:"/><s:property value="releaseCompany"/><br>
	                           		<s:label value="発売日:"/><s:property value="releaseDate"/>
	                           	</div>
	                           	<div class="subtotal"><s:label value="合計金額:"/><s:property value="subtotal"/>円</div>
	                       	</div>
	                   		<s:hidden name="productName" value="%{productName}"/>
	                   		<s:hidden name="productNameKana" value="%{productNameKana}"/>
	                   		<s:hidden name="imageFilePath" value="%{imageFilePath}"/>
	                   		<s:hidden name="imageFileName" value="%{imageFileName}"/>
	                   		<s:hidden name="price" value="%{price}"/>
	                  		<s:hidden name="releaseCompany" value="%{releaseCompany}"/>
	                   		<s:hidden name="releaseDate" value="%{releaseDate}"/>
	                   		<s:hidden name="productCount" value="%{productCount}"/>
	                   		<s:hidden name="subtotal" value="%{subtotal}"/>
	                   	</div>
					</s:iterator>
					<h2 class="cart-total-price"><s:label value="カート合計金額："/><s:property value="#session.totalPrice"/>円</h2><br>
					<div class="submit_btn_box">
						<div id=".contents-btn-set">
							<s:submit value="決済" class="submit_kanryou"/>
						</div>
					</div>
					<br><br>
					<div class="submit_btn_box">
						<div id=".contents-btn-set">
							<s:submit value="削除" onclick="this.form.action='DeleteCartAction';" class="submit_kyoukyo"/>
						</div>
					</div><br>
				</s:form>
			</s:if>
		</div>
		<s:else>
			<div>カート情報はありません。</div>
		</s:else>
		<div><s:include value="footer.jsp"/></div>
	</body>
</html>