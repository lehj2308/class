<%@ tag language="java" pageEncoding="UTF-8"%>


<%@ attribute name="name" %>

<jsp:useBean id="productBean" class="model.ProductBean" scope="session" />
		<select name="${name}">
			<%
				for (String v : productBean.getProduct()) {
					out.println("<option>"+v+"</option>");
				}
			%>
		</select>