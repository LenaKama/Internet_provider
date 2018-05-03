<%@page contentType="text/html;charset=UTF-8" language="java"%>



<ul class="nav nav-tabs" role = "tablist">
    <li class="nav-item">
        <a class="nav-link active" href="#profile" data-toggle="tab" role="tab">Profile</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#about" data-toggle="tab" role="tab">About</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#1" data-toggle="tab" role="tab">1</a>
    </li>
</ul>
<div class="tab-content">
    <div class="tab-pane active" id="profile" role="tabpanel">
        <%@include file="client_account.jsp"%>
    </div>
    <div class="tab-pane" id="about" role="tabpanel">
       hello
    </div>
    <div class="tab-pane" id="1" role="tabpanel">
        hello1
    </div>
</div>
