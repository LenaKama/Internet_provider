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
        document.getElementById("${active_locale}").classList.add('active');
    }
</script>

<ul class="nav nav-pills nav-justified">
        <li id="general">
            <form action="/Controller">
                <input type="hidden" name="command" value="show_general">
                <input class="menu-item" type="submit" value='<fmt:message key="menu.general" bundle="${loc}"/>'/>
            </form>
        </li>
        <c:if test="${sessionScope.usRole eq 'admin'}">
            <li id="clients">
                <form action="/Controller">
                    <input type="hidden" name="command" value="show_clients">
                    <input class="menu-item" type="submit" value='<fmt:message key="menu.clients" bundle="${loc}"/>'/>
                </form>
            </li>
            <li id="messages">
            <form action="/Controller">
                <input type="hidden" name="command" value="show_messages">
                <input class="menu-item" type="submit" value='<fmt:message key="menu.messages" bundle="${loc}"/>'/>
            </form>
        </li>
        </c:if>
        <li id="traffic_status">
            <form action="/Controller">
                <input type="hidden" name="command" value="show_traffic_status">
                <input class="menu-item" type="submit" value='<fmt:message key="menu.trafficStatus" bundle="${loc}"/>'/>
            </form>
        </li>
        <li id="sessions">
            <form action="/Controller">
                <input type="hidden" name="command" value="show_sessions">
                <input class="menu-item" type="submit" value='<fmt:message key="menu.sessions" bundle="${loc}"/>'/>
            </form>
        </li>
        <li id="transactions">
            <form action="/Controller">
                <input type="hidden" name="command" value="show_transactions">
                <input class="menu-item" type="submit" value='<fmt:message key="menu.transactions" bundle="${loc}"/>'/>
            </form>
        </li>
        <li id="account_settings">
            <form action="/Controller">
                <input type="hidden" name="command" value="show_account_settings">
                <input class="menu-item" type="submit" value='<fmt:message key="menu.accountSettings" bundle="${loc}"/>'/>
            </form>
        </li>
    <li id="logout">
        <form action="/Controller">
            <input type="hidden" name="command" value="log_out">
            <input class="menu-item" type="submit" value='<fmt:message key="menu.sign_out" bundle="${loc}"/>'/>
        </form>
    </li>
</ul>
