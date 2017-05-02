docker run --name my-openldap-container -p 389:389 --detach osixia/openldap:1.1.8

docker exec my-openldap-container ldapsearch -x -H ldap://localhost -b dc=example,dc=org -D "cn=admin,dc=example,dc=org" -w admin
(Brings back response, LDAP is running)


I connected JXplorer:
- Be sure to disable SELinux (sudo setenforce 0)
- Add port 389 to docker startup (docker run --name my-openldap-container -p 389:389 --detach osixia/openldap:1.1.8)
- Use credentials admin/admin, be sure to specify admin DN as cn=admin,dc=example,dc=org