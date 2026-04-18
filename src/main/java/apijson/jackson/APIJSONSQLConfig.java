/*Copyright ©2025 APIJSON(https://github.com/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.jackson;

import apijson.RequestMethod;
import apijson.jackson.javax.APIJSONApplication;
import apijson.orm.AbstractSQLConfig;
import apijson.orm.Join;
import apijson.orm.SQLConfig;

import java.util.*;



/**SQL配置
 * TiDB 用法和 MySQL 一致
 * @author Lemon
 */
public class APIJSONSQLConfig<T> extends apijson.framework.APIJSONSQLConfig<T, Map<String, Object>, List<Object>> {
	public static final String TAG = "APIJSONSQLConfig";

	static {
		SIMPLE_CALLBACK = new SimpleCallback<Object>() {};
	}


	public APIJSONSQLConfig() {
		this(RequestMethod.GET);
	}

	public APIJSONSQLConfig(RequestMethod method) {
		super(method);
	}

	public APIJSONSQLConfig(RequestMethod method, String table) {
		super(method, table);
	}

	public APIJSONSQLConfig(RequestMethod method, int count, int page) {
		super(method, count, page);
	}


	/**
	 * 获取SQL配置
	 *
	 * @param table
	 * @param alias
	 * @param request
	 * @param isProcedure
	 * @return
	 * @throws Exception
	 */
	//public static <T> SQLConfig<T, Map<String, Object>, List<Object>> newSQLConfig(
	//		RequestMethod method, String table, String alias, Map<String, Object> request, List<Join<T
	//		, Map<String, Object>, List<Object>>> joinList, boolean isProcedure) throws Exception {
	//	return newSQLConfig(method, table, alias, request, joinList, isProcedure, new SimpleCallback<T>() {});
	//}
	public static <T> SQLConfig<T, Map<String, Object>, List<Object>> newSQLConfig2(
			RequestMethod method, String table, String alias
			, Map<String, Object> request, List<Join<T, Map<String, Object>, List<Object>>> joinList, boolean isProcedure
	) throws Exception {
		return newSQLConfig(method, table, alias, request, joinList, isProcedure, (SimpleCallback<T>) SIMPLE_CALLBACK);
	}

	public static class SimpleCallback<T> extends AbstractSQLConfig.SimpleCallback<T, Map<String, Object>, List<Object>> {

		@Override
		public SQLConfig<T, Map<String, Object>, List<Object>> getSQLConfig(RequestMethod method, String database
				, String datasource, String namespace, String catalog, String schema, String table) {
			SQLConfig<T, Map<String, Object>, List<Object>> config = APIJSONApplication.createSQLConfig();
			config.setMethod(method);
			config.setDatabase(database);
			config.setDatasource(datasource);
			config.setNamespace(namespace);
			config.setCatalog(catalog);
			config.setSchema(schema);
			config.setTable(table);
			return config;
		}
	}
}