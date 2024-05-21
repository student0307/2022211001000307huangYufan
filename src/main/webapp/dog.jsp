<%@ page import="com.HuangYufan.lab1.Person" %>
<%@ page import="com.HuangYufan.lab1.Dog" %><%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/5/4
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo 2 week10</title>
</head>
<body>
<%
    Person person=new Person();
    person.setName("Evan");

    Dog dog=new Dog();
    dog.setName("Tommy");
    dog.setAge(8);

    person.setDog(dog);

    request.setAttribute("person",person);
%>

<h1>Get Person's Dog name Using java code </h1>
<%
    Person p= (Person) request.getAttribute("person");
    Dog d=p.getDog();
    out.println(p.getName()+"s' dog is "+d.getName());
%>
<br>
<%
    out.println(p.getName()+"s' dog is "+d.getAge()+"years old");
%>
<h1>get person's dog name using EL code </h1>
Evan's dog name:${person.dog.name}<br>
The dog is ${person.dog.age} years old
</body>
</html>
