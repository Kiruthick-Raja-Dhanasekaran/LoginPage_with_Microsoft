/**
 * created By :maheshshelke2
 * created On :15-Jun-2023-5:18:23 pm
 */
package com.bajaj.ADID.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author maheshshelke2
 *
 */
@Service
@Slf4j
public class LoginService {

	public Map<String, String> loginADID(String userId, String pwd) {

		String AdUserId = "bfl" + "\\" + userId;
		String auth_type = "SIMPLE";
		String ldapUrl = "ldap://10.128.1.50:3268/dc=bfl,dc=com";
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapUrl);
		env.put(Context.SECURITY_AUTHENTICATION, auth_type);
		env.put(Context.SECURITY_PRINCIPAL, AdUserId);
		env.put(Context.SECURITY_CREDENTIALS, pwd);
		Map<String, String> result = new HashMap<String, String>();
		try {

			DirContext dirContext = new InitialDirContext(env);
			String searchFilter = "(&(objectClass=*)(SAMAccountName=" + userId + "))";// WROKING
			final SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

			final NamingEnumeration<?> searchResults = dirContext.search("", searchFilter, constraints);

			while (searchResults != null && searchResults.hasMoreElements()) {
				log.info("Login success for user " + userId);
				result.put("IS_LOGIN", "true");
				break;
			}

			while (searchResults.hasMoreElements()) {

				SearchResult sr = (SearchResult) searchResults.next();
				Attributes attrs = sr.getAttributes();
				String data = attrs.toString();
				try {
					String[] array = data.split(",");
					for (String string : array) {
						if (string.contains("=") && string.contains(":")) {
							result.put(string.split("=")[0], string.split(":")[1]);
						}
					}
				} catch (Exception e) {
					
				}

			}

		} catch (Exception e) {
			if (e.getMessage().contains("data 52e")) {
				result.put("IS_LOGIN", "false-Invalid Credentials");
			} else {
				result.put("IS_LOGIN", "false-Unable to login");
			}
			return result;
		}
		return result;
	}
}
