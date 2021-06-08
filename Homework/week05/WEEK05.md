## WEEK05

**1.（选做）**使 Java 里的动态代理，实现一个简单的 AOP。
**2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。**

1. xml配置

   ```
   <bean id="studentByXml" class="com.sky.entity.Student">
           <property name="firstName" value="Micheal"/>
           <property name="lastName" value="Jordan"/>
       </bean>
   ```

   

   ```java
   @Test
       public void testXml() {
           ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           Student studentByXml = (Student) context.getBean("studentByXml");
           System.out.println(studentByXml);
   
           Object klass1 = context.getBean("klass1");
           System.out.println(klass1);
       }
   ```

   

2. 注解配置

   ```java
   @Test
       public void testAnnotation() {
           ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           Object school = context.getBean("school");
           System.out.println(school);
       }
   ```

3. Javaconfig

   ```java
   @Configuration
   public class MyConfig {
   
       @Bean("dept1")
       public Department getDept() {
           return new Department(1, "研发部");
       }
   
       @Bean("student3")
       public Student getStuent() {
           return new Student("Stephen", "Curry", 30);
       }
   
   }
   ```

   

   ```java
   @Test
       public void testJavaConfig() {
           AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
           Object student = context.getBean("student3");
           System.out.println(student);
       }
   ```

   

**3.（选做）**实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。

**4.（选做，会添加到高手附加题）**
4.1 （挑战）讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；
4.2 （挑战）基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；
4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；
4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；
4.5 （超级挑战）尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。

**5.（选做）**总结一下，单例的各种写法，比较它们的优劣。
**6.（选做）**maven/spring 的 profile 机制，都有什么用法？
**7.（选做）**总结 Hibernate 与 MyBatis 的各方面异同点。
**8.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。**

还是有些问题：

属性类：

```java
@ConfigurationProperties(prefix = "auto.edu.student")
public class StudentProperties {
    String firstName;
    String lastName;
    int age;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

自动配置类：

```java
@Slf4j
@EnableConfigurationProperties({StudentProperties.class,KlassProperties.class,SchoolProperties.class})
public class EducationAutoConfiguration {
    @Autowired
    StudentProperties properties;
    @Autowired
    KlassProperties klassProperties;
    @Autowired
    SchoolProperties schoolProperties;
    @Bean
    public Student student(){
        log.info("装载student");
        Student student = new Student(properties.firstName, properties.lastName, properties.age);
        return student;
    }
    @Bean
    @ConditionalOnClass(Student.class)
    public Klass klass(){
        log.info("装载klass");
        Klass klass = new Klass();
        klass.setKlassName(klassProperties.klassName);
        klass.setStudent(klassProperties.getStudent());
        return klass;
    }
    @Bean
    @ConditionalOnClass(Klass.class)
    public School school(){
        log.info("scholl");
        School school = new School();
        school.setSchoolName(schoolProperties.schoolName);
        return school;
    }
}
```

spring.factories:

```xml
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.sky.autoconfig.EducationAutoConfiguration
```



**9.（选做**）学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。
**10.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：**
1）使用 JDBC 原生接口，实现数据库的增删改查操作。

[DBUtil 工具类](https://github.com/SkyMing1024/java-advanced-exercise/blob/main/java-advanced-project/src/main/java/com/sky/week05spring/springboot01/src/main/java/com/sky/jdbc/DBUtil.java) 

直接连接：[TestJdbc.java ](https://github.com/SkyMing1024/java-advanced-exercise/blob/main/java-advanced-project/src/main/java/com/sky/week05spring/springboot01/src/main/java/com/sky/jdbc/TestJdbc.java)

2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。

[TestJdbc2.java ](https://github.com/SkyMing1024/java-advanced-exercise/blob/main/java-advanced-project/src/main/java/com/sky/week05spring/springboot01/src/main/java/com/sky/jdbc/TestJdbc2.java)

3）配置 Hikari 连接池，改进上述操作。提交代码到 GitHub。

springboot中使用Hikari：

[TestJdbc3.java ](https://github.com/SkyMing1024/java-advanced-exercise/blob/main/java-advanced-project/src/main/java/com/sky/week05spring/springboot01/src/main/java/com/sky/jdbc/TestJdbc3.java)

```xml
<dependency>
            <groupId>com.github.dreamroute</groupId>
            <artifactId>hikari-spring-boot-starter</artifactId>
            <version>1.4-RELEASE</version>
</dependency>
```



**附加题（可以后面上完数据库的课再考虑做）：**
(挑战) 基于 AOP 和自定义注解，实现 @MyCache(60) 对于指定方法返回值缓存 60 秒。
(挑战) 自定义实现一个数据库连接池，并整合 Hibernate/Mybatis/Spring/SpringBoot。
(挑战) 基于 MyBatis 实现一个简单的分库分表 + 读写分离 + 分布式 ID 生成方案。