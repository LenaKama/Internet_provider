<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="table">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="transactions.table.date" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="transactions.table.status" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="transactions.table.sum" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="transactions.table.Info" bundle="${loc}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="transaction" items="${transactionList}" varStatus="status">
        <tr>
            <td>${transaction.trDate}</td>
            <td>
                <c:if test="${transaction.trSum > 0}">
                    <label class="label-success"><fmt:message key="transaction.status.debited" bundle="${loc}"/></label>
                </c:if>
                <c:if test="${transaction.trSum < 0}">
                    <label class="label-danger"><fmt:message key="transaction.status.credited" bundle="${loc}"/></label>
                </c:if>
            </td>
            <td>${transaction.trSum}</td>
            <td>${transaction.trInfo}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
