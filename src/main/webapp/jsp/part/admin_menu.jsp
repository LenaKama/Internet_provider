<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="nav nav-pills nav-justified">
    <li id="general" class="menu-item">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="show_general">
            <input type="submit" value='<fmt:message key="client_account.nav.general" bundle="${loc}"/>'/>
        </form>
    </li>
    <li id="users" class="menu-item">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="show_user_list">
            <input type="submit" value='<fmt:message key="users.button.users" bundle="${loc}"/>'/>
        </form>
    </li>
    <li id="traffic_status" class="menu-item">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="show_traffic_status">
            <input type="submit" value='<fmt:message key="client_account.nav.trafficStatus" bundle="${loc}"/>'/>
        </form>
    </li>
    <li id="sessions" class="menu-item">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="show_sessions">
            <input type="submit" value='<fmt:message key="client_account.nav.sessions" bundle="${loc}"/>'/>
        </form>
    </li>
    <li id="transactions" class="menu-item">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="show_transactions">
            <input type="submit" value='<fmt:message key="client_account.nav.transactions" bundle="${loc}"/>'/>
        </form>
    </li>
    <li id="account_settings" class="menu-item">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="show_account_settings">
            <input type="submit" value='<fmt:message key="client_account.nav.accountSettings" bundle="${loc}"/>'/>
        </form>
    </li>
</ul>