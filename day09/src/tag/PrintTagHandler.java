package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PrintTagHandler extends SimpleTagSupport{

	private String color;
	private String fontSize;
	
	public void doTag() throws IOException, JspException {
		JspWriter out= getJspContext().getOut();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<div style= 'color:").append(color).append("; font-size:").append(fontSize).append(";' >");
		
		JspFragment body= getJspBody();
		if(body!=null) {
			out.println(sb.toString());
			body.invoke(null);
			out.println("</div>");
		}
		
		
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	
	
}
