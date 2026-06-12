# spring-authentication-authorization

## Screenshots
<img width="1917" height="946" alt="1" src="https://github.com/user-attachments/assets/6ba43017-074c-4e9b-af5c-836516b1d040" />
<img width="1919" height="807" alt="2" src="https://github.com/user-attachments/assets/daada963-d39e-4506-91a4-fb9b7f79c7a3" />
<img width="1919" height="803" alt="3" src="https://github.com/user-attachments/assets/2c76a76f-54e3-44a6-b12c-36189119bf05" />
<img width="1916" height="946" alt="4" src="https://github.com/user-attachments/assets/19e7a918-5a32-4a53-a17a-408bd97f20b1" />
<img width="1919" height="943" alt="5" src="https://github.com/user-attachments/assets/0e133095-2db3-4e6f-a817-00afcc13695e" />
<img width="1919" height="943" alt="6" src="https://github.com/user-attachments/assets/6724662b-5ff5-42d8-9cf3-94f6ed0448d0" />
<img width="1918" height="941" alt="7" src="https://github.com/user-attachments/assets/f9e7f06b-6ff2-4f4f-b7a3-81a332ae16d9" />
<img width="1919" height="943" alt="8" src="https://github.com/user-attachments/assets/637ed66c-6a1a-408a-8034-50f07b711bd6" />
<img width="1919" height="936" alt="9" src="https://github.com/user-attachments/assets/f900e83c-e863-48cb-ac59-c97bccb2461a" />




## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.6/maven-plugin/build-image.html)
* [Thymeleaf](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html)
* [Spring Security](https://docs.spring.io/spring-boot/4.0.6/reference/web/spring-security.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
