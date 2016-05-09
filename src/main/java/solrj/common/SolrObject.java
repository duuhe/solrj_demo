package solrj.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrObject {
	public static Object toBean(SolrDocument doc, Class<?> clazz)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Object o = null;
		o = clazz.newInstance();

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Object value = doc.get(field.getName());
			BeanUtils.setProperty(o, field.getName(), value);
		}

		return o;
	}

	public static Object toBeanList(SolrDocumentList docs, Class<?> clazz) {
		List<Object> list = new ArrayList<Object>();

		if (docs == null) {
			return list;
		}

		for (SolrDocument doc : docs) {
			try {
				list.add(toBean(doc, clazz));
			} catch (InstantiationException | IllegalAccessException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
