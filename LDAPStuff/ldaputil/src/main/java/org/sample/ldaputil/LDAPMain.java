package org.sample.ldaputil;
import java.security.MessageDigest;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;
 

public class LDAPMain {
 
    private Logger logger = Logger.getLogger(LDAPMain.class);
    private Hashtable<String, String> env = new Hashtable<String, String>();
 
    public LDAPMain() {
        try {
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:389");
            env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=org");
            env.put(Context.SECURITY_CREDENTIALS, "admin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
    }
 
    private boolean insert(Person person) {
        try {
 
            DirContext dctx = new InitialDirContext(env);
            Attributes matchAttrs = new BasicAttributes(true);
            //matchAttrs.put(new BasicAttribute("uid", person.getName()));
            matchAttrs.put(new BasicAttribute("cn", person.getName()));
            //matchAttrs.put(new BasicAttribute("street", person.getAddress()));
            //matchAttrs.put(new BasicAttribute("sn", person.getName()));
            //matchAttrs.put(new BasicAttribute("userPassword", encryptLdapPassword("SHA", person.getPassword())));
            matchAttrs.put(new BasicAttribute("objectclass", "simpleSecurityObject"));
            matchAttrs.put(new BasicAttribute("objectclass", "organizationalRole"));
            //matchAttrs.put(new BasicAttribute("objectclass", "organizationalPerson"));
            //matchAttrs.put(new BasicAttribute("objectclass", "inetorgperson"));
            String name = "cn=" + person.getName() + ",dc=example, dc=org";
            InitialDirContext iniDirContext = (InitialDirContext) dctx;
            iniDirContext.bind(name, dctx, matchAttrs);
 
            System.out.println("success inserting "+person.getName());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
 
    private boolean edit(Person person) {
        try {
            System.out.println("Starting edit");
            DirContext ctx = new InitialDirContext(env);
            ModificationItem[] mods = new ModificationItem[2];
            Attribute mod0 = new BasicAttribute("street", person.getAddress());
            // Attribute mod1 = new BasicAttribute("userpassword", encryptLdapPassword("SHA", person.getPassword()));
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);
            //mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod1);
 
            ctx.modifyAttributes("cn=" + person.getName() + ",dc=example,dc=org", mods);
 
            System.out.println("success editing "+person.getName());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
 
    private boolean delete(Person person) {
        try {
 
            DirContext ctx = new InitialDirContext(env);
            ctx.destroySubcontext("cn=" + person.getName() + ",dc=example, dc=org");
 
            System.out.println("success deleting "+person.getName());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
     
    private boolean search(Person person) {
        try {
 
        	System.out.println("Starting search");
            DirContext ctx = new InitialDirContext(env);
            String base = "dc=example,dc=org";
 
            SearchControls sc = new SearchControls();
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
 
            String filter = "(&(objectclass=organizationalRole)(cn="+person.getName()+"))";
 
            NamingEnumeration results = ctx.search(base, filter, sc);
            
 
            while (results.hasMore()) {
                SearchResult sr = (SearchResult) results.next();
                Attributes attrs = sr.getAttributes();
 
                Attribute attr = attrs.get("cn");
                if(attr != null)
                    System.out.println("record found "+attr.get());
            }
            ctx.close();
            System.out.println("Ending search");             
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
 
    private String encryptLdapPassword(String algorithm, String _password) {
        String sEncrypted = _password;
        if ((_password != null) && (_password.length() > 0)) {
            boolean bMD5 = algorithm.equalsIgnoreCase("MD5");
            boolean bSHA = algorithm.equalsIgnoreCase("SHA")
                    || algorithm.equalsIgnoreCase("SHA1")
                    || algorithm.equalsIgnoreCase("SHA-1");
            if (bSHA || bMD5) {
                String sAlgorithm = "MD5";
                if (bSHA) {
                    sAlgorithm = "SHA";
                }
                try {
                    MessageDigest md = MessageDigest.getInstance(sAlgorithm);
                    md.update(_password.getBytes("UTF-8"));
                    sEncrypted = "{" + sAlgorithm + "}" + (new BASE64Encoder()).encode(md.digest());
                } catch (Exception e) {
                    sEncrypted = null;
                    System.out.println(e.getMessage());
                }
            }
        }
        return sEncrypted;
    }
 
    public static void main(String[] args) {
        LDAPMain main = new LDAPMain();
 
        Person person = new Person();
        person.setAddress("kebayoran");
        person.setName("kamplenk");
        person.setPassword("pepe");
 
        // insert
        main.insert(person);
         
        // edit
        main.edit(person);
         
        // select
        main.search(person);
         
        // delete
        main.delete(person);
    }
}