package servlet;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@SuppressWarnings("serial")
public class servletConfig extends mainServlet {
	Configuration cfg;
	private String templateDir = "/WEB-INF/templates";

	public void config(Configuration cfg) {
		this.cfg = cfg;
		this.cfg = new Configuration(Configuration.VERSION_2_3_28);
		File file = new File(getServletContext().getRealPath(templateDir));
		try {
			this.cfg.setDirectoryForTemplateLoading(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cfg.setDefaultEncoding("UTF-8");
		this.cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		this.cfg.setLogTemplateExceptions(false);
		this.cfg.setWrapUncheckedExceptions(true);
	}
}
