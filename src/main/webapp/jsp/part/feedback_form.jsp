<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="feedback_form">
    <p id="feedback_button">Feedback</p>
<div id="check"></div>
    <form id="feedback" method="post" action="/Controller">
        <input type="hidden" name="command" value="add_feedback">
        <input type="text" id="usName" name="fName" required pattern="^[a-zA-Zа-яА-Я]{1,}"
               placeholder='<fmt:message key="feedback.name.title" bundle="${loc}"/>'>
        <br>
        <input type="text" id="email" name="fEmail" required pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$"
               placeholder='<fmt:message key="feedback.email.title" bundle="${loc}"/>'>
        <br>
        <textarea id="message" name="fMessage" required pattern="^[^<>/]"
                  placeholder='<fmt:message key="feedback.message.title" bundle="${loc}"/>'></textarea>
        <br>
        <input type="submit" id="submit_feedback" value='<fmt:message key="form.button.enter" bundle="${loc}"/>'>

    </form>
</div>
