package com.u2a.framework.util;


import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;

import freemarker.cache.TemplateLoader;

public class TextLoader implements TemplateLoader {

	public void closeTemplateSource(Object arg0) throws IOException {

	}

	public Object findTemplateSource(String arg0) throws IOException {
		int i=arg0.indexOf("_zh_");
		String id=arg0;
		if (i>-1) {
			id= arg0.substring(0, i);
		}
		return arg0;
	}

	public long getLastModified(Object arg0) {
		Date d= new Date();
		return d.getTime();
	}

	public Reader getReader(Object arg0, String arg1) throws IOException {
		return new StringReader((String)arg0);
	}

}
