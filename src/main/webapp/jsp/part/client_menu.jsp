<%@page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${empty activeMenu}">
        <c:set var="active_menu" value="general"/>
    </c:when>
    <c:otherwise>
        <c:set var="active_menu" value="${activeMenu}"/>
    </c:otherwise>
</c:choose>

<script>
    window.onload = function () {
        document.getElementById("${active_menu}").classList.add('active');
    }
</script>

<ul class="nav nav-pills nav-justified">
        <li id="general" class="menu-item">
            <form action="/Controller" method="post">
                <input type="hidden" name="command" value="show_general">
                <input type="submit" value='<fmt:message key="client_account.nav.general" bundle="${loc}"/>'/>
            </form>
        </li>
        <li id="messages" class="menu-item">
            <form action="/Controller" method="post">
                <input type="hidden" name="command" value="show_messages">
                <input type="submit" value='<fmt:message key="client_account.nav.messages" bundle="${loc}"/>'/>
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
    <li id="logout" class="menu-item">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="log_out">
            <input type="submit" value='<fmt:message key="menu.logout" bundle="${loc}"/>'/>
        </form>
    </li>
</ul>
