package org.sample;

import java.util.List;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

public class LdapUtil {

	public static void main(String[] args) {
		System.out.println("Hey");
        LdapContextSource ctxSrc = new LdapContextSource();
        ctxSrc.setUrl("ldap://localhost:389");
        ctxSrc.setBase("dc=example,dc=org");
        ctxSrc.setUserDn("cn=admin,dc=example,dc=org");
        ctxSrc.setPassword("admin");
        ctxSrc.afterPropertiesSet();

        LdapTemplate tmpl = new LdapTemplate(ctxSrc);

        PersonDao dao = new PersonDao(tmpl);
        dao.getAllPersonNames();
        
        List retList = dao.getAllContactNames();
        for (Object obj : retList){
        	System.out.println("List Object type:" + obj.getClass().getName());
        	System.out.println("Obj toString:" + obj.toString());
        }
        
        
        
    }

    public static class PersonDao {

        private LdapTemplate ldapTemplate;

        public PersonDao(LdapTemplate ldapTemplate) {
            this.ldapTemplate = ldapTemplate;
        }

        public void setLdapTemplate(LdapTemplate ldapTemplate) {
            this.ldapTemplate = ldapTemplate;
        }

        public List getAllPersonNames() {
            //EqualsFilter filter = new EqualsFilter("objectclass", "organizationalRole");
        	EqualsFilter filter = new EqualsFilter("objectclass", "organizationalRole");
            return ldapTemplate.search(DistinguishedName.EMPTY_PATH,
                    filter.encode(),
                    new AttributesMapper() {

                        public Object mapFromAttributes(Attributes attrs) throws NamingException {
                            return attrs.get("cn").get();
                        }
                    });
        }
        
        
        public List getAllContactNames() {
    		return ldapTemplate.search("", "(objectClass=organizationalRole)",
    				new AttributesMapper() {
    					public Object mapFromAttributes(Attributes attrs)
    							throws NamingException {
    						return attrs.get("description").get();
    					}
    				});
    	}
     
        /**
    	public List getContactDetails(String objectclass){
    		AndFilter andFilter = new AndFilter();
    		andFilter.and(new EqualsFilter("objectClass",objectclass));
    		System.out.println("LDAP Query " + andFilter.encode());
    		return ldapTemplate.search("", andFilter.encode(),new ContactAttributeMapper());
     
    	}
    	**/
        
        
    }

}
