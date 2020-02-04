<%--
  Created by IntelliJ IDEA.
  User: agata
  Date: 03.02.2020
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>

<div>
    <form method="post" enctype="multipart/form-data" action="/imageUpload">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td></td>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <td><input type="submit" value="Upload"/></td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>
